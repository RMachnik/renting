package rent.domain.user.notification;

import rent.repo.api.Repositories;
import rent.repo.api.user.NotificationPreferenceDto;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

public class NotificationPreferences {

    private final long userId;
    private final Repositories repositories;

    public NotificationPreferences(long userId, Repositories repositories) {
        this.userId = userId;
        this.repositories = repositories;
    }

    public List<NotificationPreference> getAllPreferences() {
        return repositories.getNotificationPreferenceRepository().getAll(userId)
                .stream()
                .map(NotificationPreference::new)
                .collect(toList());
    }

    public void initDefaults() {
        repositories.getNotificationPreferenceRepository().initDefaults(userId, getAllTypes());
    }

    private List<NotificationPreferenceDto> getAllTypes() {
        return of(NotificationType.values())
                .map((notificationType) -> new NotificationPreferenceDto() {
                    @Override
                    public String getType() {
                        return notificationType.name();
                    }

                    @Override
                    public boolean isActive() {
                        return false;
                    }

                    @Override
                    public String getTemplate() {
                        return notificationType.getDefaultTemplate();
                    }
                })
                .collect(toList());
    }
}
