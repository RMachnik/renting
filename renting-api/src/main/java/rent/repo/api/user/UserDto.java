package rent.repo.api.user;

public interface UserDto {

    long getId();

    String getPassword();

    String getFirstName();

    String getLastName();

    String getEmail();

    boolean isActive();
}
