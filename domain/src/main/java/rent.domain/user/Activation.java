package rent.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import rent.repo.api.Repositories;
import rent.repo.api.user.ActivationDetailsDto;
import rent.repo.api.user.ActivationDto;
import rent.repo.api.user.UserDto;

import java.util.UUID;

import static lombok.AccessLevel.NONE;

/**
 * Class responsible for activating account and reseting password.
 */

@Getter
@EqualsAndHashCode
public class Activation {

    private final long userId;
    private final Email email;
    private final String activationToken;
    @Getter(NONE)
    private final Repositories repositories;

    /**
     * Constructor should be used via activation controller when user has clicked on activation link.
     *
     * @param activationDto token
     * @param repositories  repositories
     */
    public Activation(ActivationDto activationDto, Repositories repositories) {
        this.repositories = repositories;
        this.activationToken = activationDto.getActivationToken();

        final ActivationDetailsDto activationDetailsDto = this.repositories
                .getUserActivationRepository()
                .getActivationDetails(activationDto);

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
        this.repositories = repositories;
        this.activationToken = UUID.randomUUID().toString();
        this.userId = userId;
        this.email = new Email(email);
    }

    public Activation(String email, Repositories repositories) {
        this.repositories = repositories;
        this.activationToken = UUID.randomUUID().toString();
        this.email = new Email(email);

        final UserDto userDto = repositories.getUserRepository().getByEmail(email);
        this.userId = userDto.getId();

        if (email.equals(userDto.getEmail())) {
            this.repositories
                    .getUserActivationRepository()
                    .sendActivationEmail(new ActivationDetailsDtoImpl(userId, userDto.getEmail(), activationToken));
            repositories.getUserRepository().inactivateUser(userId);
        }
    }

    public void activateAccount() {
        repositories.getUserRepository().activateUser(userId);
        repositories
                .getUserActivationRepository()
                .activateAccount(() -> activationToken);
    }

    public void sendActivationEmail() {
        repositories.getUserRepository().inactivateUser(userId);
        repositories
                .getUserActivationRepository()
                .sendActivationEmail(
                        new ActivationDetailsDtoImpl(userId, email.getAddress(), activationToken)
                );

    }

    /**
     * Sets new password and activate user again.
     *
     * @param newPassword
     */
    public void setNewPassword(String newPassword) {
        repositories.getUserRepository().changePassword(userId, newPassword);
        repositories.getUserRepository().activateUser(userId);
        repositories.getUserActivationRepository().remove(activationToken);
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
