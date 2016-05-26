package rent.rest.security.auth;

public enum UserRole {
    USER;

    public String getRole() {
        return "ROLE_" + name();
    }
}
