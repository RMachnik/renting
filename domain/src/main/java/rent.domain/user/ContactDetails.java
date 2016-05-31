package rent.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import rent.repo.api.user.ContactDetailsDto;

/**
 * Data that will be included into bills for system usage.
 */
@Getter
@EqualsAndHashCode
public class ContactDetails {

    private final String fullName;
    private final Address address;
    private final String identification;
    private final PhoneNumber phoneNumber;

    public ContactDetails(ContactDetailsDto contactDetailsDto) {
        this.fullName = contactDetailsDto.getFullName();
        this.address = new Address(contactDetailsDto.getAddress());
        this.identification = contactDetailsDto.getIdentification();
        this.phoneNumber = new PhoneNumber(contactDetailsDto.getPhoneNumber());
    }
}
