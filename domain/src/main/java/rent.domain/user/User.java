package rent.domain.user;

import rent.repo.api.Repositories;
import rent.repo.api.user.UserAuthenticationDto;
import rent.repo.api.user.UserRepository;

import java.util.Optional;

import static java.util.Optional.of;

public class User {

    private final long id;
    private final String userName;
    private final String password;
    private final transient UserRepository userRepository;
    private Optional<UserDetails> userDetails = Optional.empty();

    public User(String userName, String password, Repositories repositories) {
        this.userRepository = repositories.getUserRepository();

        UserAuthenticationDto userAuthenticationDto = userRepository.getUserAuthentication(userName, password);
        this.id = userAuthenticationDto.getUserId();
        this.userName = userAuthenticationDto.getUserName();
        this.password = userAuthenticationDto.getPassword();
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public UserDetails getUserDetails() {
        if (userDetails.isPresent()) {
            userDetails = of(new UserDetails(userRepository.getUserDetails()));
        }
        return userDetails.get();
    }
}
