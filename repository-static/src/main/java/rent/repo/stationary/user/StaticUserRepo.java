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
import static rent.repo.stationary.user.StaticEmailDto.EMAIL_DTO;

public class StaticUserRepo implements UserRepository {

    public static final UserDto USER_DTO = new UserDto() {
        @Override
        public long getId() {
            return 1;
        }

        @Override
        public String getPassword() {
            return "password";
        }

        @Override
        public String getFirstName() {
            return "firstname";
        }

        @Override
        public String getLastName() {
            return "lastname";
        }

        @Override
        public String getEmail() {
            return EMAIL_DTO;
        }

        @Override
        public boolean isActive() {
            return true;
        }
    };
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
    public UserDto getByEmail(String email) {
        return USERS.stream()
                .filter(userDto -> userDto.getEmail().equals(email))
                .findFirst()
                .get();
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
    public void inactivateUser(long userId) {
    }

    @Override
    public void changePassword(long userId, String newPassword) {

    }

    @Override
    public UserDto authenticate(String email, String password) {
        return USERS.stream()
                .filter(userDto -> userDto.getEmail().equals(email) && userDto.getPassword().equals(password))
                .findFirst()
                .get();
    }
}
