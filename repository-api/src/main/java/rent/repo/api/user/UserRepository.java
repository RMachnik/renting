package rent.repo.api.user;

public interface UserRepository extends UserAuthenticationRepository {

    void addUser(UserAuthenticationDto userAuthenticationDto);

    UserDetailsDto getUserDetails();
}
