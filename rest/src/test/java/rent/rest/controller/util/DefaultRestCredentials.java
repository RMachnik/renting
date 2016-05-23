package rent.rest.controller.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultRestCredentials implements RestCredentials {

    @Value("${rest.test.username}")
    public String username;

    @Value("${rest.test.password}")
    public String password;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
