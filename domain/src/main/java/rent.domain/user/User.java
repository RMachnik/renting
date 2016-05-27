package rent.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import rent.repo.api.Repositories;
import rent.repo.api.user.RegistrationDto;
import rent.repo.api.user.UserDto;
import rent.repo.api.user.UserRepository;

import java.util.Optional;

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
    private Optional<UserDetails> userDetails = Optional.empty();

    public User(String userName, String password, Repositories repositories) {
        this.userRepository = repositories.getUserRepository();

        UserDto userDto = userRepository.authenticate(userName, password);
        this.id = userDto.getId();
        this.userName = userDto.getUserName();
        this.password = userDto.getPassword();
        this.email = new Email(userDto.getEmail());
        this.active = userDto.isActive();
    }

    //todo this operation should trigger sending activation email
    public User(RegistrationDto registrationDto, Repositories repositories) {
        this.userRepository = repositories.getUserRepository();
        this.userName = registrationDto.getUserName();
        this.password = registrationDto.getPassword();
        this.email = new Email(registrationDto.getEmail());
        this.active = false;

        this.id = userRepository.addUser(registrationDto);
        new Activation(id, email.getAddress(), repositories).sendActivationEmail();

    }
    public UserDetails getUserDetails() {
        if (userDetails.isPresent()) {
            userDetails = of(new UserDetails(userRepository.getUserDetails()));
        }
        return userDetails.get();
    }
}
