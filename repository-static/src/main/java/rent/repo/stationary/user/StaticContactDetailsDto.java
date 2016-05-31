package rent.repo.stationary.user;

import rent.repo.api.user.AddressDto;
import rent.repo.api.user.ContactDetailsDto;

import static rent.repo.stationary.user.StaticAddressDto.ADDRESS_DTO;

public class StaticContactDetailsDto {

    public static final ContactDetailsDto CONTACT_DETAILS_DTO = new ContactDetailsDto() {
        @Override
        public String getFullName() {
            return "FULL_NAME";
        }

        @Override
        public AddressDto getAddress() {
            return ADDRESS_DTO;
        }

        @Override
        public String getIdentification() {
            return "1234ask2341lk";
        }

        @Override
        public String getPhoneNumber() {
            return "123 456 7890";
        }
    };
}
