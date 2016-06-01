package rent.repo.stationary.user;

import com.google.common.collect.Lists;
import rent.repo.api.user.NotificationPreferenceDto;
import rent.repo.api.user.NotificationPreferenceRepository;

import java.util.List;

public class StaticNotificationPrefRepo implements NotificationPreferenceRepository {
    @Override
    public List<NotificationPreferenceDto> getAll(long id) {
        return Lists.newArrayList(StaticNotificationDto.NOTIFICATION_DTO);
    }
}
