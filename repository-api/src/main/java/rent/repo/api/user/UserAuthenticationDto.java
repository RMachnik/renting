package rent.repo.api.user;

public interface UserAuthenticationDto {

    long getUserId();

    String getPassword();

    String getUserName();

    String getLastName();
}
