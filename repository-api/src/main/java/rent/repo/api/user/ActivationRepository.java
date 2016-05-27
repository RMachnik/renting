package rent.repo.api.user;

public interface ActivationRepository {

    void sendActivationEmail(ActivationDetailsDto activationDetailsDto);

    void activateAccount(ActivationDto activationDto);

    ActivationDetailsDto getActivationDetails(ActivationDto activationDto);
}
