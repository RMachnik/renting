package rent.domain.user;

import org.junit.Test;
import rent.repo.stationary.StaticRepositories;

import static org.fest.assertions.Assertions.assertThat;
import static rent.repo.stationary.user.StaticUserAuthenticationDto.USER_AUTH_DTO;

public class UserTest {

    @Test
    public void shouldConstructProperUser() {
        User user = new User(USER_AUTH_DTO.getUserName(), USER_AUTH_DTO.getPassword(), new StaticRepositories());

        assertThat(user.getId()).isEqualTo(USER_AUTH_DTO.getUserId());
        assertThat(user.getUserName()).isEqualTo(USER_AUTH_DTO.getUserName());
        assertThat(user.getPassword()).isEqualTo(USER_AUTH_DTO.getPassword());
    }
}