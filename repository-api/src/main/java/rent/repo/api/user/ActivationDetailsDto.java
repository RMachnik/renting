package rent.repo.api.user;

public interface ActivationDetailsDto {

    long getId();

    long getUserId();

    String getEmail();

    String getActivationToken();
}
