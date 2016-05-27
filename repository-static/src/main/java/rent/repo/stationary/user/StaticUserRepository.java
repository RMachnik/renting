package rent.repo.stationary.user;

import rent.repo.api.user.RegistrationDto;
import rent.repo.api.user.UserDetailsDto;
import rent.repo.api.user.UserDto;
import rent.repo.api.user.UserRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static rent.repo.stationary.user.StaticUserDto.USER_DTO;

public class StaticUserRepository implements UserRepository {

    public static final List<UserDto> USERS = newArrayList(USER_DTO);

    @Override
    public long addUser(RegistrationDto registrationDto) {
        return USER_DTO.getId();
    }

    @Override
    public UserDetailsDto getUserDetails() {
        throw new NotImplementedException();
    }

    @Override
    public void activateUser(long userId) {
        //static user is active
    }

    @Override
    public UserDto getUser(long id) {
        return USER_DTO.getId() == id ? USER_DTO : null;
    }

    @Override
    public UserDto authenticate(String userName, String password) {
        return USERS.stream()
                .filter(userDto -> userDto.getUserName().equals(userName) && userDto.getPassword().equals(password))
                .findFirst()
                .get();
    }
}
