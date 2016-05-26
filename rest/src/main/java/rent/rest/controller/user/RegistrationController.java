package rent.rest.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rent.domain.user.User;
import rent.repo.api.Repositories;
import rent.repo.api.user.RegistrationDto;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static rent.common.util.SerializationUtil.fromJson;

@Slf4j
@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    Repositories repositories;

    @ResponseBody
    @RequestMapping(method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody String body) {
        try {
            final RegistrationDto registrationDto = fromJson(body, RegistrationForm.class);
            User user = new User(registrationDto, repositories);
        } catch (RuntimeException ex) {
            log.warn("Unable to register user.", ex);
            return badRequest().build();
        }
        return ok("OK");
    }

    static class RegistrationForm implements RegistrationDto {
        final String username;
        final String password;
        final String email;

        RegistrationForm(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;
        }

        @Override
        public String getUserName() {
            return username;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getEmail() {
            return email;
        }
    }
}
