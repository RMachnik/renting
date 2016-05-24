package rent.repo.api.user;

public interface UserRepository extends UserAuthenticationRepository {

    void createUser(UserAuthenticationDto userAuthenticationDto);

    UserDetailsDto getUserDetails();
}
