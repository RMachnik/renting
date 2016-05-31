package rent.repo.stationary.user;


import rent.rest.api.RegistrationDto;

import static rent.repo.stationary.user.StaticEmailDto.EMAIL_DTO;
import static rent.repo.stationary.user.StaticUserDto.USER_DTO;

public class StaticRegistrationDto {

    public static final RegistrationDto REGISTRATION_DTO = new RegistrationDto() {
        @Override
        public String getFirstName() {
            return USER_DTO.getFirstName();
        }

        @Override
        public String getPassword() {
            return USER_DTO.getPassword();
        }

        @Override
        public String getEmail() {
            return EMAIL_DTO;
        }
    };

}
