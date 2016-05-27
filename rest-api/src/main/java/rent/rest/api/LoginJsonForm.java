package rent.rest.api;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginJsonForm {

    private final String username;
    private final String password;

    LoginJsonForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken getAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
