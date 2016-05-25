package rent.repo.stationary.user;

import rent.repo.api.user.UserAuthenticationDto;
import rent.repo.api.user.UserDetailsDto;
import rent.repo.api.user.UserRepository;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static rent.repo.stationary.user.StaticUserAuthenticationDto.USER_AUTH_DTO;

public class StaticUserRepository implements UserRepository {

    public static final List<UserAuthenticationDto> USERS = newArrayList(USER_AUTH_DTO);

    @Override
    public void addUser(UserAuthenticationDto userAuthenticationDto) {

    }

    @Override
    public UserDetailsDto getUserDetails() {
        return null;
    }

    @Override
    public UserAuthenticationDto getUserAuthentication(String userName, String password) {
        return USERS.stream()
                .filter(userDto -> userDto.getUserName().equals(userName) && userDto.getPassword().equals(password))
                .findFirst()
                .get();
    }
}
