package rent.rest.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class SessionUser implements Serializable {
    private static final long serialVersionUID = 1;

    private final long userId;

    public SessionUser(long userId) {
        this.userId = userId;
    }
}
