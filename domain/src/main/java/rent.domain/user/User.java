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

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.NONE;

@Getter
@ToString
@EqualsAndHashCode
public class User {

    private long id;
    private String firstName;
    private Optional<String> lastName;
    private String password;
    private Email email;
    private boolean active;
    private List<NotificationPreference> notificationPreferences;

    @Getter(NONE)
    private final transient UserRepository userRepository;

    //todo implement adding default notification preferences
    public User(long id, Repositories repositories) {
        this.userRepository = repositories.getUserRepository();

        UserDto userDto = userRepository.getUser(id);
        loadData(repositories, userDto);
    }

    public User(String firstName, String password, Repositories repositories) {
        this.userRepository = repositories.getUserRepository();

        UserDto userDto = userRepository.authenticate(firstName, password);
        loadData(repositories, userDto);
    }

    private void loadData(Repositories repositories, UserDto userDto) {
        this.id = userDto.getId();
        this.firstName = userDto.getFirstName();
        this.lastName = ofNullable(userDto.getLastName());
        this.password = userDto.getPassword();
        this.email = new Email(userDto.getEmail());
        this.active = userDto.isActive();
        this.notificationPreferences = repositories.getNotificationPreferenceRepository().getAll(id)
                .stream()
                .map(NotificationPreference::new)
                .collect(toList());
    }

    public User(RegistrationDto registrationDto, Repositories repositories) {
        this.userRepository = repositories.getUserRepository();
        this.firstName = registrationDto.getFirstName();
        this.lastName = empty();
        this.password = registrationDto.getPassword();
        this.email = new Email(registrationDto.getEmail());
        this.active = false;

        this.id = userRepository.addUser(registrationDto);
        new Activation(id, email.getAddress(), repositories).sendActivationEmail();
        this.notificationPreferences = initPreferences(id, repositories);
    }

    private List<NotificationPreference> initPreferences(long id, Repositories repositories) {
        return newArrayList();
    }

    public void addMainContactDetails(ContactDetailsDto contactDetailsDto) {
        userRepository.addContactDetails(id, contactDetailsDto);
    }

    public void addInvoiceContactDetails(InvoiceContactDetailsDto invoiceContactDetailsDto) {
        userRepository.addInvoiceContactDetails(id, invoiceContactDetailsDto);
    }

    public Optional<InvoiceContactDetails> getInvoiceContactDetails() {
        return ofNullable(userRepository.getInvoiceContactDetails(id)).map(InvoiceContactDetails::new);
    }

    public Optional<ContactDetails> getMainContactDetails() {
        return ofNullable(userRepository.getContactDetails(id)).map(ContactDetails::new);
    }
}
