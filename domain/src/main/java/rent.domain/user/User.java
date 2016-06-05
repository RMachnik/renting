package rent.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import rent.domain.user.notificaton.NotificationType;
import rent.repo.api.Repositories;
import rent.repo.api.user.ContactDetailsDto;
import rent.repo.api.user.InvoiceContactDetailsDto;
import rent.repo.api.user.NotificationPreferenceDto;
import rent.repo.api.user.UserDto;
import rent.rest.api.RegistrationDto;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
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
    private boolean active;

    /**
     * Constructor to get user domain object. Should be invoked in rest when we have currently logged in user with his id.
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
     * Constructor that should be used to authenticate user.
     *
     * @param firstName
     * @param password
     * @param repositories
     */
    public User(String firstName, String password, Repositories repositories) {
        this.repositories = repositories;

        UserDto userDto = repositories.getUserRepository().authenticate(firstName, password);
        initData(userDto);
    }

    /**
     * Constructor that should be used to register user.
     * This constructor initialize default preferences and sends activation email.
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
        repositories.getNotificationPreferenceRepository().initDefaults(id, getAllTypes());

        new Activation(id, email.getAddress(), repositories).sendActivationEmail();
    }

    private void initData(UserDto userDto) {
        this.id = userDto.getId();
        this.firstName = userDto.getFirstName();
        this.lastName = ofNullable(userDto.getLastName());
        this.password = userDto.getPassword();
        this.email = new Email(userDto.getEmail());
        this.active = userDto.isActive();
    }

    private List<NotificationPreferenceDto> getAllTypes() {
        return of(NotificationType.values())
                .map((notificationType) -> new NotificationPreferenceDto() {
                    @Override
                    public String getType() {
                        return notificationType.name();
                    }

                    @Override
                    public boolean isActive() {
                        return false;
                    }

                    @Override
                    public String getTemplate() {
                        return notificationType.getDefaultTemplate();
                    }
                })
                .collect(toList());
    }

    public List<NotificationPreference> getNotificationPreferences() {
        return repositories.getNotificationPreferenceRepository().getAll(id)
                .stream()
                .map(NotificationPreference::new)
                .collect(toList());
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
