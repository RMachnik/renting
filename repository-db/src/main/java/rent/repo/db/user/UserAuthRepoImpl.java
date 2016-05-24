package rent.repo.db.user;

import rent.repo.api.user.UserAuthenticationDto;
import rent.repo.api.user.UserDetailsDto;
import rent.repo.api.user.UserRepository;

import java.util.List;

import static com.google.common.collect.Iterables.getFirst;

public class UserAuthRepoImpl implements UserRepository {

    private final UserCrudRepository userCrudRepository;

    public UserAuthRepoImpl(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }

    @Override
    public UserAuthenticationDto getUserAuthentication(String userName, String password) {
        List<UserAuthenticationDto> userDto = userCrudRepository.findByUserName(userName);
        return getFirst(userDto, null);
    }

    @Override
    public void createUser(UserAuthenticationDto userAuthenticationDto) {
        UserAuthenticationEntity authDto = new UserAuthenticationEntity(userAuthenticationDto);
        userCrudRepository.save(authDto);
    }

    @Override
    public UserDetailsDto getUserDetails() {
        return null;
    }
}
