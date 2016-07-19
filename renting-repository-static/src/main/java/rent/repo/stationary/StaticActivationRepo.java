package rent.repo.stationary;

import rent.repo.api.user.ActivationDetailsDto;
import rent.repo.api.user.ActivationDto;
import rent.repo.api.user.ActivationRepository;

import static rent.repo.stationary.user.StaticActivationDto.ACTIVATION_DETAILS_DTO;

public class StaticActivationRepo implements ActivationRepository {
    @Override
    public void sendActivationEmail(ActivationDetailsDto activationDetailsDto) {

    }

    @Override
    public void activateAccount(ActivationDto activationDto) {

    }

    @Override
    public ActivationDetailsDto getActivationDetails(ActivationDto activationDto) {
        return ACTIVATION_DETAILS_DTO;
    }

    @Override
    public void remove(String activationToken) {

    }
}
