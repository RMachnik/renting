package rent.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import rent.repo.api.Repositories;
import rent.repo.api.user.ContactDetailsDto;
import rent.repo.api.user.InvoiceContactDetailsDto;
import rent.repo.api.user.UserDto;
import rent.rest.api.RegistrationDto;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.NONE;

@Getter
@ToString
@EqualsAndHashCode
public class User {

    @Getter(NONE)
    private final transient Repositories repositories;

    private long id;
    private String firstName;
    private Optional<String> lastName;
    private String password;
    private Email email;
    private NotificationPreferences notificationPreferences;
    private boolean active;

    /**
     * Constructor for getting user domain object. Should be invoked in rest when we have currently logged in user with his id.
     *
     * @param id
     * @param repositories
     */
    public User(long id, Repositories repositories) {
        this.repositories = repositories;

        UserDto userDto = repositories.getUserRepository().getUser(id);
        initData(userDto);
    }

    /**
     * Constructor for authenticating user.
     *
     * @param email
     * @param password
     * @param repositories
     */
    public User(String email, String password, Repositories repositories) {
        this.repositories = repositories;

        UserDto userDto = repositories.getUserRepository().authenticate(email, password);
        initData(userDto);
    }

    /**
     * Constructor for registering user.
     * This constructor initialize default preferences and sends activation email.
     *
     * @param registrationDto
     * @param repositories
     */
    public User(RegistrationDto registrationDto, Repositories repositories) {
        this.repositories = repositories;

        this.firstName = registrationDto.getFirstName();
        this.password = registrationDto.getPassword();
        this.email = new Email(registrationDto.getEmail());
        this.id = repositories.getUserRepository().addUser(registrationDto);

        //default params
        this.lastName = empty();
        this.active = false;
        this.notificationPreferences = new NotificationPreferences(id, repositories);
        notificationPreferences.initDefaults();

        new Activation(id, email.getAddress(), repositories).sendActivationEmail();
    }

    private void initData(UserDto userDto) {
        this.id = userDto.getId();
        this.firstName = userDto.getFirstName();
        this.lastName = ofNullable(userDto.getLastName());
        this.password = userDto.getPassword();
        this.email = new Email(userDto.getEmail());
        this.active = userDto.isActive();
        this.notificationPreferences = new NotificationPreferences(id, repositories);
    }

    public List<NotificationPreference> getNotificationPreferences() {
        return notificationPreferences.getAllPreferences();
    }

    public void addMainContactDetails(ContactDetailsDto contactDetailsDto) {
        repositories.getUserRepository().addContactDetails(id, contactDetailsDto);
    }

    public void addInvoiceContactDetails(InvoiceContactDetailsDto invoiceContactDetailsDto) {
        repositories.getUserRepository().addInvoiceContactDetails(id, invoiceContactDetailsDto);
    }

    public Optional<InvoiceContactDetails> getInvoiceContactDetails() {
        return ofNullable(repositories.getUserRepository().getInvoiceContactDetails(id)).map(InvoiceContactDetails::new);
    }

    public Optional<ContactDetails> getMainContactDetails() {
        return ofNullable(repositories.getUserRepository().getContactDetails(id)).map(ContactDetails::new);
    }
}
