package rent.repo.db.user;

import rent.repo.api.user.NotificationPreferenceDto;
import rent.repo.api.user.NotificationPreferenceRepository;
import rent.repo.db.user.entity.NotificationPreferencesEntity;

import java.util.List;

public class NotificationPreferencesRepoImpl implements NotificationPreferenceRepository {

    private final NotificationPrefCrudRepo notificationPrefCrudRepo;

    public NotificationPreferencesRepoImpl(NotificationPrefCrudRepo notificationPrefCrudRepo) {
        this.notificationPrefCrudRepo = notificationPrefCrudRepo;
    }

    @Override
    public List<NotificationPreferenceDto> getAll(long userId) {
        return notificationPrefCrudRepo.findByUserId(userId);
    }

    @Override
    public void initDefaults(long userId, List<NotificationPreferenceDto> dtos) {
        for (NotificationPreferenceDto dto : dtos) {
            notificationPrefCrudRepo.save(new NotificationPreferencesEntity(userId, dto));
        }
    }
}
