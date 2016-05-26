package rent.repo.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rent.repo.api.user.SessionUserDto;
import rent.repo.api.user.UserRepository;

import static org.fest.assertions.Assertions.assertThat;
import static rent.repo.stationary.user.StaticRegistrationDto.REGISTRATION_DTO;
import static rent.repo.stationary.user.StaticSessionUserDto.SESSION_USER_DTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
        RepoDbConfig.class,
})

public class UserRepoImplTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void shouldAddAndGetUser() {
        userRepository.addUser(REGISTRATION_DTO);

        SessionUserDto userAuthentication = userRepository.authenticate(SESSION_USER_DTO.getUserName(), SESSION_USER_DTO.getPassword());

        assertThat(userAuthentication.getUserName()).isEqualTo(SESSION_USER_DTO.getUserName());
        assertThat(userAuthentication.getUserId()).isEqualTo(SESSION_USER_DTO.getUserId());
        assertThat(userAuthentication.getPassword()).isEqualTo(SESSION_USER_DTO.getPassword());
    }
}
