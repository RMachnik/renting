package rent.domain.security;

import rent.repo.api.user.UserAuthenticationDto;

import java.io.Serializable;

public class SessionUser implements Serializable {
    private static final long serialVersionUID = 1;
    private final long userId;


    public SessionUser(UserAuthenticationDto userAuthenticationDto) {
        this.userId = userAuthenticationDto.getUserId();
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionUser that = (SessionUser) o;

        return userId == that.userId;

    }

    @Override
    public int hashCode() {
        return (int) userId;
    }
}
