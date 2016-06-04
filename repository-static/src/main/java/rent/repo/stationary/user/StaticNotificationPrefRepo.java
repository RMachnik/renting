package rent.repo.stationary.user;

import rent.repo.api.user.NotificationPreferenceDto;
import rent.repo.api.user.NotificationPreferenceRepository;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static rent.repo.stationary.user.StaticNotificationDto.NOTIFICATION_DTO;

public class StaticNotificationPrefRepo implements NotificationPreferenceRepository {

    public static final ArrayList<NotificationPreferenceDto> NOTIFICATION_PREFERENCE_DTOS = newArrayList(NOTIFICATION_DTO);

    @Override
    public List<NotificationPreferenceDto> getAll(long id) {
        return NOTIFICATION_PREFERENCE_DTOS;
    }

    @Override
    public void initDefaults(long userId, List<NotificationPreferenceDto> dtos) {

    }
}
