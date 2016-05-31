package rent.repo.stationary.user;

import rent.repo.api.user.ContactDetailsDto;
import rent.repo.api.user.InvoiceContactDetailsDto;
import rent.repo.api.user.UserDto;
import rent.repo.api.user.UserRepository;
import rent.rest.api.RegistrationDto;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static rent.repo.stationary.user.StaticContactDetailsDto.CONTACT_DETAILS_DTO;
import static rent.repo.stationary.user.StaticContactDetailsDto.INVOICE_CONTACT_DETAILS_DTO;
import static rent.repo.stationary.user.StaticUserDto.USER_DTO;

public class StaticUserRepository implements UserRepository {

    public static final List<UserDto> USERS = newArrayList(USER_DTO);

    @Override
    public long addUser(RegistrationDto registrationDto) {
        return USER_DTO.getId();
    }

    @Override
    public void activateUser(long userId) {
        //static user is active
    }

    @Override
    public UserDto getUser(long id) {
        return USER_DTO.getId() == id ? USER_DTO : null;
    }

    @Override
    public ContactDetailsDto getContactDetails(long userId) {
        return CONTACT_DETAILS_DTO;
    }

    @Override
    public void addContactDetails(long userId, ContactDetailsDto contactDetailsDto) {

    }

    @Override
    public void addInvoiceContactDetails(long userId, InvoiceContactDetailsDto invoiceContactDetailsDto) {

    }

    @Override
    public InvoiceContactDetailsDto getInvoiceContactDetails(long userId) {
        return INVOICE_CONTACT_DETAILS_DTO;
    }

    @Override
    public UserDto authenticate(String email, String password) {
        return USERS.stream()
                .filter(userDto -> userDto.getEmail().equals(email) && userDto.getPassword().equals(password))
                .findFirst()
                .get();
    }
}
