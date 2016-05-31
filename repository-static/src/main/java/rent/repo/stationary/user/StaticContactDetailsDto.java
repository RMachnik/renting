package rent.repo.stationary.user;

import rent.repo.api.user.AddressDto;
import rent.repo.api.user.ContactDetailsDto;
import rent.repo.api.user.InvoiceContactDetailsDto;

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

    public static final InvoiceContactDetailsDto INVOICE_CONTACT_DETAILS_DTO = new InvoiceContactDetailsDto() {
        @Override
        public String getAccountNumber() {
            return "12345670985345";
        }

        @Override
        public String getBankName() {
            return "IDEA";
        }

        @Override
        public String getPaymentMethod() {
            return "CASH";
        }

        @Override
        public String getFullName() {
            return CONTACT_DETAILS_DTO.getFullName();
        }

        @Override
        public AddressDto getAddress() {
            return CONTACT_DETAILS_DTO.getAddress();
        }

        @Override
        public String getIdentification() {
            return CONTACT_DETAILS_DTO.getIdentification();
        }

        @Override
        public String getPhoneNumber() {
            return CONTACT_DETAILS_DTO.getPhoneNumber();
        }
    };
}
