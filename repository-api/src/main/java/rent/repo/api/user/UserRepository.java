package rent.repo.api.user;

import rent.rest.api.RegistrationDto;

public interface UserRepository extends AuthRepository {

    long addUser(RegistrationDto registrationDto);

    ContactDetailsDto getUserDetails();

    void activateUser(long userId);

    UserDto getUser(long id);

    ContactDetailsDto getContactDetails(long userId);
}
