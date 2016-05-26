package rent.repo.stationary.user;

import rent.repo.api.user.RegistrationDto;
import rent.repo.api.user.SessionUserDto;
import rent.repo.api.user.UserDetailsDto;
import rent.repo.api.user.UserRepository;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static rent.repo.stationary.user.StaticSessionUserDto.SESSION_USER_DTO;

public class StaticUserRepository implements UserRepository {

    public static final List<SessionUserDto> USERS = newArrayList(SESSION_USER_DTO);

    @Override
    public long addUser(RegistrationDto registrationDto) {
        return SESSION_USER_DTO.getUserId();
    }

    @Override
    public UserDetailsDto getUserDetails() {
        return null;
    }

    @Override
    public SessionUserDto authenticate(String userName, String password) {
        return USERS.stream()
                .filter(userDto -> userDto.getUserName().equals(userName) && userDto.getPassword().equals(password))
                .findFirst()
                .get();
    }
}
