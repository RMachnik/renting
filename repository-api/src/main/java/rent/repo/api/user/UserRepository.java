package rent.repo.api.user;

import rent.rest.api.RegistrationDto;

public interface UserRepository extends AuthRepository {

    long addUser(RegistrationDto registrationDto);

    UserDetailsDto getUserDetails();

    void activateUser(long userId);

    UserDto getUser(long id);
}
