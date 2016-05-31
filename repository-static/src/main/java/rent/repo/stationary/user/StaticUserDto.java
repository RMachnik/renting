package rent.repo.stationary.user;

import rent.repo.api.user.UserDto;

import static rent.repo.stationary.user.StaticEmailDto.EMAIL_DTO;

public class StaticUserDto {

    public static final UserDto USER_DTO = new UserDto() {
        @Override
        public long getId() {
            return 1;
        }

        @Override
        public String getPassword() {
            return "password";
        }

        @Override
        public String getFirstName() {
            return "firstname";
        }

        @Override
        public String getLastName() {
            return "lastname";
        }

        @Override
        public String getEmail() {
            return EMAIL_DTO;
        }

        @Override
        public boolean isActive() {
            return true;
        }
    };
}
