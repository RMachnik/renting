package rent.repo.stationary.user;

import rent.repo.api.user.UserDto;

public class StaticUserDto {

    public static final UserDto SESSION_USER_DTO = new UserDto() {
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
