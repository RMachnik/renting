package rent.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import rent.domain.user.notification.NotificationType;
import rent.repo.api.user.NotificationPreferenceDto;

@Getter
@EqualsAndHashCode
public class NotificationPreference {

    private final NotificationType notificationType;
    private final boolean active;
    private final String template;


    public NotificationPreference(NotificationPreferenceDto dto) {
        this.notificationType = NotificationType.valueOf(dto.getType());
        this.active = dto.isActive();
        this.template = dto.getTemplate();
    }
}
