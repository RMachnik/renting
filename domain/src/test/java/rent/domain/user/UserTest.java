package rent.domain.user;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import rent.domain.user.notificaton.NotificationType;
import rent.repo.api.Repositories;
import rent.repo.api.user.ActivationRepository;
import rent.repo.api.user.NotificationPreferenceDto;
import rent.repo.api.user.NotificationPreferenceRepository;
import rent.repo.api.user.UserRepository;
import rent.repo.stationary.StaticRepositories;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static rent.repo.stationary.user.StaticContactDetailsDto.CONTACT_DETAILS_DTO;
import static rent.repo.stationary.user.StaticContactDetailsDto.INVOICE_CONTACT_DETAILS_DTO;
import static rent.repo.stationary.user.StaticNotificationPrefRepo.NOTIFICATION_PREFERENCE_DTOS;
import static rent.repo.stationary.user.StaticRegistrationDto.REGISTRATION_DTO;
import static rent.repo.stationary.user.StaticUserDto.USER_DTO;

public class UserTest {

    static final List<NotificationPreferenceDto> NOTIFICATION_PREFS = of(NotificationType.values())
            .map((type) -> new NotificationPreferenceDto() {
                @Override
                public String getType() {
                    return type.name();
                }

                @Override
                public boolean isActive() {
                    return false;
                }

                @Override
                public String getTemplate() {
                    return type.getDefaultTemplate();
                }
            })
            .collect(toList());
    Repositories repositories = mock(Repositories.class);
    UserRepository userRepository = mock(UserRepository.class);
    ActivationRepository activationRepository = mock(ActivationRepository.class);
    NotificationPreferenceRepository notificationPreferenceRepository = mock(NotificationPreferenceRepository.class);

    @Before
    public void setUp() throws Exception {
        when(repositories.getUserRepository()).thenReturn(userRepository);
        when(repositories.getUserActivationRepository()).thenReturn(activationRepository);
        when(repositories.getNotificationPreferenceRepository()).thenReturn(notificationPreferenceRepository);

        when(userRepository.addUser(REGISTRATION_DTO)).thenReturn(USER_DTO.getId());
        when(userRepository.getUser(USER_DTO.getId())).thenReturn(USER_DTO);

        doNothing().when(activationRepository).sendActivationEmail(any());

        when(notificationPreferenceRepository.getAll(USER_DTO.getId())).thenReturn(newArrayList());
        doNothing().when(notificationPreferenceRepository).initDefaults(USER_DTO.getId(), NOTIFICATION_PREFS);
    }

    @Test
    public void shouldConstructProperUser() {
        User user = new User(USER_DTO.getEmail(), USER_DTO.getPassword(), new StaticRepositories());

        assertThat(user.getId()).isEqualTo(USER_DTO.getId());
        assertThat(user.getFirstName()).isEqualTo(USER_DTO.getFirstName());
        assertThat(user.getLastName().get()).isEqualTo(USER_DTO.getLastName());
        assertThat(user.getEmail()).isEqualTo(new Email(USER_DTO.getEmail()));
        assertThat(user.getPassword()).isEqualTo(USER_DTO.getPassword());
        assertThat(user.isActive()).isEqualTo(USER_DTO.isActive());
        assertThat(user.getMainContactDetails().isPresent()).isTrue();
        assertThat(user.getInvoiceContactDetails().isPresent()).isTrue();
        assertThat(user.getNotificationPreferences().size()).isEqualTo(NOTIFICATION_PREFERENCE_DTOS.size());
    }

    @Test
    public void shouldInitDefaultNotificationPrefsDuringRegistration() {
        new User(REGISTRATION_DTO, repositories);

        verify(notificationPreferenceRepository).initDefaults(eq(USER_DTO.getId()), argThat(new ArgumentMatcher<List<NotificationPreferenceDto>>() {
            @Override
            public boolean matches(Object argument) {
                List<NotificationPreferenceDto> prefs = (List<NotificationPreferenceDto>) argument;
                return prefs.size() == NOTIFICATION_PREFS.size();
            }
        }));
    }

    @Test
    public void shouldAddContactDetails() {
        User user = new User(USER_DTO.getId(), repositories);

        user.addMainContactDetails(CONTACT_DETAILS_DTO);

        verify(userRepository).addContactDetails(user.getId(), CONTACT_DETAILS_DTO);
    }

    @Test
    public void shouldAddInvoiceDetails() {
        User user = new User(USER_DTO.getId(), repositories);

        user.addInvoiceContactDetails(INVOICE_CONTACT_DETAILS_DTO);

        verify(userRepository).addInvoiceContactDetails(user.getId(), INVOICE_CONTACT_DETAILS_DTO);
    }

}
