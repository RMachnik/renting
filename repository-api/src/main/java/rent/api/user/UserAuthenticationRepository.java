package rent.api.user;


public interface UserAuthenticationRepository {

    UserAuthenticationDto getUserAuthentication(String userName, String password);
}
