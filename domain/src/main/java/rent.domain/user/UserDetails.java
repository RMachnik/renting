package rent.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import rent.repo.api.user.UserDetailsDto;

@Getter
@EqualsAndHashCode
public class UserDetails {

    private Gender gender;

    public UserDetails(UserDetailsDto userDetails) {

    }
}
