package rent.repo.stationary.user;

import rent.repo.api.user.RegistrationDto;

import static rent.repo.stationary.user.StaticUserDto.SESSION_USER_DTO;

public class StaticRegistrationDto {

    public static final RegistrationDto REGISTRATION_DTO = new RegistrationDto() {
        @Override
        public String getUserName() {
            return SESSION_USER_DTO.getUserName();
        }

        @Override
        public String getPassword() {
            return SESSION_USER_DTO.getPassword();
        }

        @Override
        public String getEmail() {
            return "email@test.com";
        }
    };

}
