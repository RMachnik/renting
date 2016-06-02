package rent.repo.api.user;

import java.util.List;

public interface NotificationPreferenceRepository {
    List<NotificationPreferenceDto> getAll(long id);

    void initDefaults(long userId, List<NotificationPreferenceDto> dtos);
}
