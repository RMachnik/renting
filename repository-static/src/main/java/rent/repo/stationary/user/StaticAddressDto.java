package rent.repo.stationary.user;

import rent.repo.api.user.AddressDto;

public class StaticAddressDto {

    public static final AddressDto ADDRESS_DTO = new AddressDto() {
        @Override
        public String getAddressLine1() {
            return "Sunny";
        }

        @Override
        public String getAddressLine2() {
            return "Avenue";
        }

        @Override
        public String getPostalCode() {
            return "390123IL";
        }

        @Override
        public String getCity() {
            return "US";
        }
    };
}
