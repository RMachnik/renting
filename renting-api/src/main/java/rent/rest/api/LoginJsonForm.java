package rent.rest.api;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginJsonForm {

    private final String email;
    private final String password;

    LoginJsonForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken getAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
