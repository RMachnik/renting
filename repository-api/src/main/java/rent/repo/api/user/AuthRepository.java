package rent.repo.api.user;


public interface AuthRepository {

    UserDto authenticate(String email, String password);
}
