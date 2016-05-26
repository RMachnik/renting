package rent.repo.api.user;


public interface AuthRepository {

    SessionUserDto authenticate(String userName, String password);
}
