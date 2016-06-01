package rent.domain.user;

import org.junit.Before;
import org.junit.Test;
import rent.repo.api.Repositories;
import rent.repo.api.user.ActivationRepository;
import rent.repo.api.user.NotificationPreferenceRepository;
import rent.repo.api.user.UserRepository;
import rent.repo.stationary.StaticRepositories;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static rent.repo.stationary.user.StaticActivationDto.ACTIVATION_DETAILS_DTO;
import static rent.repo.stationary.user.StaticContactDetailsDto.CONTACT_DETAILS_DTO;
import static rent.repo.stationary.user.StaticContactDetailsDto.INVOICE_CONTACT_DETAILS_DTO;
import static rent.repo.stationary.user.StaticUserDto.USER_DTO;

public class UserTest {

    Repositories repositories = mock(Repositories.class);
    UserRepository userRepository = mock(UserRepository.class);
    ActivationRepository activationRepository = mock(ActivationRepository.class);
    NotificationPreferenceRepository notificationPreferenceRepository = mock(NotificationPreferenceRepository.class);

    @Before
    public void setUp() throws Exception {
        when(repositories.getUserRepository()).thenReturn(userRepository);
        when(userRepository.getUser(USER_DTO.getId())).thenReturn(USER_DTO);
        doNothing().when(activationRepository).sendActivationEmail(ACTIVATION_DETAILS_DTO);
        when(repositories.getNotificationPreferenceRepository()).thenReturn(notificationPreferenceRepository);
        when(notificationPreferenceRepository.getAll(USER_DTO.getId())).thenReturn(newArrayList());
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
        assertThat(user.getNotificationPreferences().size()).isEqualTo(1);
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
