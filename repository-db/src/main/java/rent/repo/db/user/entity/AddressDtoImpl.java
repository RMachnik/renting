package rent.repo.db.user.entity;

import rent.repo.api.user.AddressDto;

public class AddressDtoImpl implements AddressDto {
    private final String addressLine1;
    private final String addressLine2;
    private final String postalCode;
    private final String city;

    AddressDtoImpl(String addressLine1, String addressLine2, String postalCode, String city) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.postalCode = postalCode;
        this.city = city;
    }

    @Override
    public String getAddressLine1() {
        return addressLine1;
    }

    @Override
    public String getAddressLine2() {
        return addressLine2;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String getCity() {
        return city;
    }
}
