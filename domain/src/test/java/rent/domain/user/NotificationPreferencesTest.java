package rent.domain.user;

import org.junit.Test;
import rent.repo.api.Repositories;
import rent.repo.api.user.NotificationPreferenceRepository;
import rent.repo.stationary.StaticRepositories;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static rent.repo.stationary.user.StaticUserDto.USER_DTO;

public class NotificationPreferencesTest {

    @Test
    public void shouldConstructNotifications() {
        NotificationPreferences notificationPreferences = new NotificationPreferences(USER_DTO.getId(), new StaticRepositories());

        assertThat(notificationPreferences.getAllPreferences().size()).isEqualTo(1);
    }

    @Test
    public void shouldInitDefaults() {
        Repositories repositories = mock(Repositories.class);
        final NotificationPreferenceRepository preferenceRepository = mock(NotificationPreferenceRepository.class);
        when(repositories.getNotificationPreferenceRepository()).thenReturn(preferenceRepository);
        doNothing().when(preferenceRepository).initDefaults(eq(USER_DTO.getId()), any());

        NotificationPreferences notificationPreferences = new NotificationPreferences(USER_DTO.getId(), repositories);
        notificationPreferences.initDefaults();

        verify(preferenceRepository).initDefaults(eq(USER_DTO.getId()), any());
    }
}
