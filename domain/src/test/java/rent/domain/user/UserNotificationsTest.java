package rent.domain.user;

import org.junit.Test;
import rent.repo.stationary.StaticRepositories;

import static org.fest.assertions.Assertions.assertThat;
import static rent.repo.stationary.user.StaticUserDto.USER_DTO;

public class UserNotificationsTest {

    @Test
    public void shouldConstructNotifications() {
        UserNotifications userNotifications = new UserNotifications(USER_DTO.getId(), new StaticRepositories());

        assertThat(userNotifications.getAllPreferences().size()).isEqualTo(1);
    }
}
