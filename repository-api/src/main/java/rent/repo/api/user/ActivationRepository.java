package rent.repo.api.user;

public interface ActivationRepository {

    void sendActivationEmail(String email, String token);

    void activateAccount(String activationDto);

    ActivationDetailsDto getActivationDetails(String activationToken);
}
