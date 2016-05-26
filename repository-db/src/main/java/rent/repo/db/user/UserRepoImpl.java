package rent.repo.db.user;

import rent.repo.api.user.RegistrationDto;
import rent.repo.api.user.SessionUserDto;
import rent.repo.api.user.UserDetailsDto;
import rent.repo.api.user.UserRepository;

import java.util.List;

import static com.google.common.collect.Iterables.getFirst;

public class UserRepoImpl implements UserRepository {

    private final UserCrudRepository userCrudRepository;

    public UserRepoImpl(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }

    @Override
    public SessionUserDto authenticate(String userName, String password) {
        List<SessionUserDto> userDto = userCrudRepository.findByUserNameAndPassword(userName, password);
        return getFirst(userDto, null);
    }

    @Override
    public long addUser(RegistrationDto sessionUserDto) {
        SessionUserEntity authDto = new SessionUserEntity(sessionUserDto);
        final SessionUserEntity savedUser = userCrudRepository.save(authDto);
        return savedUser.getUserId();
    }

    @Override
    public UserDetailsDto getUserDetails() {
        return null;
    }
}
