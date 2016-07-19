package rent.repo.db.user.entity;

import rent.repo.api.user.AddressDto;
import rent.repo.api.user.ContactDetailsDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ContactDetailsEntity implements ContactDetailsDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;
    private String fullName;
    private String identification;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String postalCode;
    private String city;

    public ContactDetailsEntity() {
    }

    public ContactDetailsEntity(long userId, ContactDetailsDto dataDto) {
        this.userId = userId;
        this.fullName = dataDto.getFullName();
        this.identification = dataDto.getIdentification();
        this.phoneNumber = dataDto.getPhoneNumber();
        this.addressLine1 = dataDto.getAddress().getAddressLine1();
        this.addressLine2 = dataDto.getAddress().getAddressLine2();
        this.postalCode = dataDto.getAddress().getPostalCode();
        this.city = dataDto.getAddress().getCity();
    }

    public long getId() {
        return id;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public AddressDto getAddress() {
        return new AddressDtoImpl(addressLine1, addressLine2, postalCode, city);
    }

    @Override
    public String getIdentification() {
        return identification;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

}
