package rent.repo.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rent.repo.api.user.UserAuthenticationDto;
import rent.repo.api.user.UserRepository;

import static org.fest.assertions.Assertions.assertThat;
import static rent.repo.stationary.user.StaticUserAuthenticationDto.USER_AUTH_DTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
        RepoDbConfig.class,
})

public class UserRepoImplTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void shouldAddAndGetUser() {
        userRepository.addUser(USER_AUTH_DTO);

        UserAuthenticationDto userAuthentication = userRepository.getUserAuthentication(USER_AUTH_DTO.getUserName(), USER_AUTH_DTO.getPassword());

        assertThat(userAuthentication.getUserName()).isEqualTo(USER_AUTH_DTO.getUserName());
        assertThat(userAuthentication.getUserId()).isEqualTo(USER_AUTH_DTO.getUserId());
        assertThat(userAuthentication.getPassword()).isEqualTo(USER_AUTH_DTO.getPassword());
    }
}