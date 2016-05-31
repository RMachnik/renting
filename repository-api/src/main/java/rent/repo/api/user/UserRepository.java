package rent.repo.api.user;

import rent.rest.api.RegistrationDto;

public interface UserRepository extends AuthRepository {

    long addUser(RegistrationDto registrationDto);

    void activateUser(long userId);

    UserDto getUser(long id);

    ContactDetailsDto getContactDetails(long userId);

    void addContactDetails(long userId, ContactDetailsDto contactDetailsDto);

    void addInvoiceContactDetails(long userId, InvoiceContactDetailsDto invoiceContactDetailsDto);

    InvoiceContactDetailsDto getInvoiceContactDetails(long userId);
}
