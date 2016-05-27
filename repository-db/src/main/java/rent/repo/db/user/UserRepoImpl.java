package rent.repo.db.user;

import rent.repo.api.user.RegistrationDto;
import rent.repo.api.user.UserDetailsDto;
import rent.repo.api.user.UserDto;
import rent.repo.api.user.UserRepository;
import rent.repo.db.user.entity.UserEntity;

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
        return savedUser.getId();
    }


    @Override
    public UserDetailsDto getUserDetails() {
        return null;
    }

    @Override
    public void activateUser(long userId) {
        final UserEntity user = userCrudRepo.findOne(userId);
        user.setActive(true);
        userCrudRepo.save(user);
    }

    @Override
    public UserDto getUser(long id) {
        final UserEntity userEnt = userCrudRepo.findOne(id);
        return new UserDtoImpl(userEnt);
    }

    static class UserDtoImpl implements UserDto {

        private final long id;
        private final String username;
        private final String email;
        private final boolean active;

        UserDtoImpl(UserEntity ent) {
            id = ent.getId();
            username = ent.getUserName();
            email = ent.getEmail();
            active = ent.isActive();

        }

        @Override
        public long getId() {
            return id;
        }

        @Override
        public String getPassword() {
            return "protected";
        }

        @Override
        public String getUserName() {
            return username;
        }

        @Override
        public String getLastName() {
            return null;
        }

        @Override
        public String getEmail() {
            return email;
        }

        @Override
        public boolean isActive() {
            return active;
        }
    }
}
