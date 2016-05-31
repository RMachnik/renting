package rent.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import rent.repo.api.Repositories;
import rent.repo.api.user.ContactDetailsDto;
import rent.repo.api.user.InvoiceContactDetailsDto;
import rent.repo.api.user.UserDto;
import rent.repo.api.user.UserRepository;
import rent.rest.api.RegistrationDto;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Getter
@ToString
@EqualsAndHashCode
public class User {

    private final long id;
    private final String userName;
    private final String password;
    private final Email email;
    private final boolean active;


    private final transient UserRepository userRepository;
    private Optional<ContactDetails> mainContactDetails = empty();
    private Optional<InvoiceContactDetails> invoiceContactDetails = empty();

    public User(String userName, String password, Repositories repositories) {
        this.userRepository = repositories.getUserRepository();

        UserDto userDto = userRepository.authenticate(userName, password);
        this.id = userDto.getId();
        this.userName = userDto.getUserName();
        this.password = userDto.getPassword();
        this.email = new Email(userDto.getEmail());
        this.active = userDto.isActive();
    }

    public User(RegistrationDto registrationDto, Repositories repositories) {
        this.userRepository = repositories.getUserRepository();
        this.userName = registrationDto.getUserName();
        this.password = registrationDto.getPassword();
        this.email = new Email(registrationDto.getEmail());
        this.active = false;

        this.id = userRepository.addUser(registrationDto);
        new Activation(id, email.getAddress(), repositories).sendActivationEmail();
    }

    public void addMainContactDetails(ContactDetailsDto contactDetailsDto) {
        userRepository.addContactDetails(id, contactDetailsDto);
        mainContactDetails = empty();
    }

    public ContactDetails getMainContactDetails() {
        if (!mainContactDetails.isPresent()) {
            mainContactDetails = of(new ContactDetails(userRepository.getContactDetails(id)));
        }
        return mainContactDetails.get();
    }

    public void addInvoiceContactDetails(InvoiceContactDetailsDto invoiceContactDetailsDto) {
        userRepository.addInvoiceContactDetails(id, invoiceContactDetailsDto);
        invoiceContactDetails = empty();
    }

    public InvoiceContactDetails getInvoiceContactDetails() {
        if (!invoiceContactDetails.isPresent()) {
            invoiceContactDetails = of(new InvoiceContactDetails(userRepository.getInvoiceContactDetails(id)));
        }
        return invoiceContactDetails.get();
    }
}
