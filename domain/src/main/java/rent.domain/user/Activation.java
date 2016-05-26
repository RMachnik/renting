package rent.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import rent.repo.api.Repositories;
import rent.repo.api.user.ActivationDetailsDto;
import rent.repo.api.user.ActivationDto;
import rent.repo.api.user.ActivationRepository;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class Activation {

    private final long userId;
    private final Email email;
    private final String activationToken;
    private final ActivationRepository activationRepository;

    public Activation(ActivationDto activationDto, Repositories repositories) {
        this.activationRepository = repositories.getUserActivationRepository();
        this.activationToken = activationDto.getActivationToken();

        final ActivationDetailsDto activationDetailsDto = activationRepository.getActivationDetails(activationToken);

        this.userId = activationDetailsDto.getUserId();
        this.email = new Email(activationDetailsDto.getEmail());
    }

    public Activation(long userId, String email, Repositories repositories) {
        this.activationRepository = repositories.getUserActivationRepository();
        this.activationToken = UUID.randomUUID().toString();
        this.userId = userId;
        this.email = new Email(email);
    }

    public void activateAcount() {
        activationRepository.activateAccount(activationToken);
    }

    public void sendActivationEmail() {
        activationRepository.sendActivationEmail(email.getAddress(), activationToken);
    }

}
