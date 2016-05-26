package rent.repo.stationary.user;

import rent.repo.api.user.SessionUserDto;

public class StaticSessionUserDto {

    public static final SessionUserDto SESSION_USER_DTO = new SessionUserDto() {
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

        @Override
        public String getEmail() {
            return "test@email.com";
        }
    };
}
