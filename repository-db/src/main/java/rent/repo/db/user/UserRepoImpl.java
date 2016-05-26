package rent.repo.db.user;

import rent.repo.api.user.RegistrationDto;
import rent.repo.api.user.UserDetailsDto;
import rent.repo.api.user.UserDto;
import rent.repo.api.user.UserRepository;

import java.util.List;

import static com.google.common.collect.Iterables.getFirst;

public class UserRepoImpl implements UserRepository {

    private final UserCrudRepo userCrudRepo;

    public UserRepoImpl(UserCrudRepo userCrudRepo) {
        this.userCrudRepo = userCrudRepo;
    }

    @Override
    public UserDto authenticate(String userName, String password) {
        List<UserDto> userDto = userCrudRepo.findByUserNameAndPassword(userName, password);
        return getFirst(userDto, null);
    }

    @Override
    public long addUser(RegistrationDto sessionUserDto) {
        UserEntity authDto = new UserEntity(sessionUserDto);
        final UserEntity savedUser = userCrudRepo.save(authDto);
        return savedUser.getUserId();
    }

    @Override
    public UserDetailsDto getUserDetails() {
        return null;
    }

    @Override
    public void activateUser(long userId) {

    }
}
