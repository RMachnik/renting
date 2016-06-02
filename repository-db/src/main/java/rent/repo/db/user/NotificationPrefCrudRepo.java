package rent.repo.db.user;

import org.springframework.data.repository.CrudRepository;
import rent.repo.api.user.NotificationPreferenceDto;
import rent.repo.db.user.entity.NotificationPreferencesEntity;

import java.util.List;

public interface NotificationPrefCrudRepo extends CrudRepository<NotificationPreferencesEntity, Long> {
    List<NotificationPreferenceDto> findByUserId(long userId);
}
