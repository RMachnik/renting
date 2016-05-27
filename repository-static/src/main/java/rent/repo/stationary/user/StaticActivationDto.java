package rent.repo.stationary.user;

import rent.repo.api.user.ActivationDetailsDto;
import rent.repo.api.user.ActivationDto;

import static rent.repo.stationary.user.StaticUserDto.USER_DTO;

public class StaticActivationDto {

    public static final ActivationDto ACTIVATION_DTO = new ActivationDto() {
        final String token = "123y56csdkjf";

        @Override
        public String getActivationToken() {
            return token;
        }

        @Override
        public boolean equals(Object obj) {
            return token.equals(((ActivationDto) obj).getActivationToken());
        }
    };

    public static final ActivationDetailsDto ACTIVATION_DETAILS_DTO = new StaticActivationDtoImpl(
            USER_DTO.getId(),
            USER_DTO.getEmail(),
            ACTIVATION_DTO.getActivationToken()
    );

    static class StaticActivationDtoImpl implements ActivationDetailsDto {

        private final long userId;
        private final String email;
        private final String activationToken;

        public StaticActivationDtoImpl(long userId, String email, String activationToken) {
            this.userId = userId;
            this.email = email;
            this.activationToken = activationToken;
        }

        @Override
        public long getId() {
            return 1;
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
            return activationToken;
        }
    }
}
