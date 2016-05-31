package rent.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import rent.repo.api.Repositories;
import rent.repo.api.user.ActivationDetailsDto;
import rent.repo.api.user.ActivationDto;
import rent.repo.api.user.ActivationRepository;

import java.util.UUID;

import static lombok.AccessLevel.NONE;

@Getter
@EqualsAndHashCode
public class Activation {

    private final long userId;
    private final Email email;
    private final String activationToken;
    @Getter(NONE)
    private final ActivationRepository activationRepository;

    /**
     * Constructor should be used via activation controller, when user clicked on activation link.
     *
     * @param activationDto token
     * @param repositories  repositories
     */
    public Activation(ActivationDto activationDto, Repositories repositories) {
        this.activationRepository = repositories.getUserActivationRepository();
        this.activationToken = activationDto.getActivationToken();

        final ActivationDetailsDto activationDetailsDto = activationRepository.getActivationDetails(activationDto);

        this.userId = activationDetailsDto.getUserId();
        this.email = new Email(activationDetailsDto.getEmail());
    }

    /**
     * Constructor for creating activation link and sending it to user.
     *
     * @param userId       userId
     * @param email        email
     * @param repositories repositories
     */
    public Activation(long userId, String email, Repositories repositories) {
        this.activationRepository = repositories.getUserActivationRepository();
        this.activationToken = UUID.randomUUID().toString();
        this.userId = userId;
        this.email = new Email(email);
    }

    public void activateAcount() {
        activationRepository.activateAccount(() -> activationToken);
    }

    public void sendActivationEmail() {
        activationRepository.sendActivationEmail(
                new ActivationDetailsDtoImpl(userId, email.getAddress(), activationToken)
        );
    }

    static class ActivationDetailsDtoImpl implements ActivationDetailsDto {

        private final long userId;
        private final String email;
        private final String token;

        public ActivationDetailsDtoImpl(long userId, String email, String token) {
            this.userId = userId;
            this.email = email;
            this.token = token;
        }

        @Override
        public long getId() {
            return 0;
        }

        @Override
        public long getUserId() {
            return userId;
        }

        @Override
        public String getEmail() {
            return email;
        }

        @Override
        public String getActivationToken() {
            return token;
        }
    }

}
