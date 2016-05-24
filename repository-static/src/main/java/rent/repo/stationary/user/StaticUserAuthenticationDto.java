package rent.repo.stationary.user;

import rent.repo.api.user.UserAuthenticationDto;

public class StaticUserAuthenticationDto {

    public static final UserAuthenticationDto USER_AUTH_DTO = new UserAuthenticationDto() {
        @Override
        public long getUserId() {
            return 1;
        }

        @Override
        public String getPassword() {
            return "password";
        }

        @Override
        public String getUserName() {
            return "username";
        }

        @Override
        public String getLastName() {
            return "lastname";
        }
    };
}
