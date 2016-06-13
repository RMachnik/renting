package rent.repo.api.user;

public interface ActivationDetailsDto extends ActivationDto {

    long getId();

    long getUserId();

    String getEmail();

    String getActivationToken();
}
