package rent.repo.db.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rent.repo.api.user.NotificationPreferenceDto;
import rent.repo.api.user.NotificationPreferenceRepository;
import rent.repo.db.RepoDbConfig;
import rent.repo.db.TestConfig;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static rent.repo.stationary.user.StaticNotificationPrefRepo.NOTIFICATION_PREFERENCE_DTOS;
import static rent.repo.stationary.user.StaticUserRepo.USER_DTO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        RepoDbConfig.class,
        TestConfig.class,
})
@ActiveProfiles("test")
public class NotificationPreferencesRepoImplTest {

    @Autowired
    NotificationPreferenceRepository notificationsRepo;

    @Test
    public void shouldAddPrefsAndByDefaultSetToActive() {
        notificationsRepo.initDefaults(USER_DTO.getId(), NOTIFICATION_PREFERENCE_DTOS);

        List<NotificationPreferenceDto> all = notificationsRepo.getAll(USER_DTO.getId());
        assertThat(all.size()).isEqualTo(NOTIFICATION_PREFERENCE_DTOS.size());
        for (NotificationPreferenceDto notificationPreferenceDto : all) {
            assertThat(notificationPreferenceDto.isActive()).isTrue();
        }
    }
}
