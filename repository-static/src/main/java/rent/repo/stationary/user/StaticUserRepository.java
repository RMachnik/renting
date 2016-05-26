package rent.repo.stationary.user;

import rent.repo.api.user.RegistrationDto;
import rent.repo.api.user.UserDetailsDto;
import rent.repo.api.user.UserDto;
import rent.repo.api.user.UserRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static rent.repo.stationary.user.StaticUserDto.SESSION_USER_DTO;

public class StaticUserRepository implements UserRepository {

    public static final List<UserDto> USERS = newArrayList(SESSION_USER_DTO);

    @Override
    public long addUser(RegistrationDto registrationDto) {
        return SESSION_USER_DTO.getUserId();
    }

    @Override
    public UserDetailsDto getUserDetails() {
        return null;
    }

    @Override
    public void activateUser(long userId) {
        throw new NotImplementedException();
    }

    @Override
    public UserDto authenticate(String userName, String password) {
        return USERS.stream()
                .filter(userDto -> userDto.getUserName().equals(userName) && userDto.getPassword().equals(password))
                .findFirst()
                .get();
    }
}
