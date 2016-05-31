package rent.repo.api.user;

public interface ContactDetailsDto {

    String getFullName();

    AddressDto getAddress();

    String getIdentification();

    String getPhoneNumber();
}
