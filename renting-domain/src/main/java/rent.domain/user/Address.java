package rent.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import rent.repo.api.user.AddressDto;

@Getter
@EqualsAndHashCode
public class Address {
    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String postalCode;

    public Address(AddressDto addressDto) {
        this.addressLine1 = addressDto.getAddressLine1();
        this.addressLine2 = addressDto.getAddressLine2();
        this.city = addressDto.getCity();
        this.postalCode = addressDto.getPostalCode();
    }
}
