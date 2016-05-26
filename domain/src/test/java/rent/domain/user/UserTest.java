package rent.domain.user;

import org.junit.Test;
import rent.repo.stationary.StaticRepositories;

import static org.fest.assertions.Assertions.assertThat;
import static rent.repo.stationary.user.StaticSessionUserDto.SESSION_USER_DTO;

public class UserTest {

    @Test
    public void shouldConstructProperUser() {
        User user = new User(SESSION_USER_DTO.getUserName(), SESSION_USER_DTO.getPassword(), new StaticRepositories());

        assertThat(user.getId()).isEqualTo(SESSION_USER_DTO.getUserId());
        assertThat(user.getUserName()).isEqualTo(SESSION_USER_DTO.getUserName());
        assertThat(user.getPassword()).isEqualTo(SESSION_USER_DTO.getPassword());
    }
}
