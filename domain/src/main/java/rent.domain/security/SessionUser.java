package rent.domain.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import rent.repo.api.user.UserDto;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class SessionUser implements Serializable {
    private static final long serialVersionUID = 1;

    private final long userId;

    public SessionUser(UserDto userDto) {
        this.userId = userDto.getId();
    }

}
