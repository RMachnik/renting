package rent.repo.api.user;

public interface UserDto {

    long getId();

    String getPassword();

    String getUserName();

    String getLastName();

    String getEmail();

    boolean isActive();
}
