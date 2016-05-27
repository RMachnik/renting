package rent.rest.api;

public class UserJson {

    private final long userId;

    public UserJson(SessionUser user) {
        this.userId = user.getUserId();
    }
}
