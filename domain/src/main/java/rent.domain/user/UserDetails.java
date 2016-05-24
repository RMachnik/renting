package rent.domain.user;

import rent.repo.api.user.UserDetailsDto;

public class UserDetails {

    private Email email;
    private Gender gender;

    public UserDetails(UserDetailsDto userDetails) {

    }

    public Email getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }
}
