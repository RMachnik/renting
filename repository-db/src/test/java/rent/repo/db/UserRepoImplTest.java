package rent.repo.db;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rent.repo.api.user.UserDto;
import rent.repo.api.user.UserRepository;

import static org.fest.assertions.Assertions.assertThat;
import static rent.repo.stationary.user.StaticRegistrationDto.REGISTRATION_DTO;
import static rent.repo.stationary.user.StaticUserDto.USER_DTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
        RepoDbConfig.class,
})

public class UserRepoImplTest {

    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.addUser(REGISTRATION_DTO);
    }

    @Test
    public void shouldAddAndGetUser() {
        UserDto userAuthentication = userRepository.authenticate(USER_DTO.getUserName(), USER_DTO.getPassword());

        assertThat(userAuthentication.getUserName()).isEqualTo(USER_DTO.getUserName());
        assertThat(userAuthentication.getId()).isEqualTo(USER_DTO.getId());
        assertThat(userAuthentication.getPassword()).isEqualTo(USER_DTO.getPassword());
    }

    @Test
    public void shouldActivateUser() {
        userRepository.activateUser(USER_DTO.getId());

        final UserDto user = userRepository.getUser(USER_DTO.getId());

        assertThat(user.isActive()).isTrue();
    }
}
