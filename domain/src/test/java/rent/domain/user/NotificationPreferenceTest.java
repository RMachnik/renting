package rent.domain.user;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static rent.repo.stationary.user.StaticNotificationDto.NOTIFICATION_DTO;

public class NotificationPreferenceTest {

    @Test
    public void shouldBasicConstructor() {
        NotificationPreference notificationPreference = new NotificationPreference(NOTIFICATION_DTO);

        assertThat(notificationPreference.getNotificationType()).isEqualTo(NotificationType.valueOf(NOTIFICATION_DTO.getType()));
        assertThat(notificationPreference.getTemplate()).isEqualTo(NOTIFICATION_DTO.getTemplate());
        assertThat(notificationPreference.isActive()).isEqualTo(NOTIFICATION_DTO.isActive());
    }
}
