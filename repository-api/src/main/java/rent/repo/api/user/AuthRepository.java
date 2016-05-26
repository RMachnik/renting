package rent.repo.api.user;


public interface AuthRepository {

    UserDto authenticate(String userName, String password);
}
