package rent.repo.api.user;

public interface UserRepository extends AuthRepository {

    long addUser(RegistrationDto registrationDto);

    UserDetailsDto getUserDetails();

    void activateUser(long userId);
}
