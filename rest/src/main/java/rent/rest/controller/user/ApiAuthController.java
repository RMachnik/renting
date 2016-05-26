package rent.rest.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rent.domain.security.SessionUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.google.common.collect.ImmutableMultimap.of;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static rent.common.util.SerializationUtil.fromJson;
import static rent.common.util.SerializationUtil.toJson;

@RestController
@RequestMapping("/auth")
public class ApiAuthController {

    static final ErrorMessage ERROR_MESSAGE = new ErrorMessage("407", "Sorry, unable to authenticate.");
    private static final Logger logger = LoggerFactory.getLogger(ApiAuthController.class);

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @ResponseBody
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity checkIfSessionExists(@AuthenticationPrincipal SessionUser user, HttpServletRequest httpRequest) {
        try {
            if (httpRequest.getSession(false) != null) {
                return ok(toJson(new UserJson(user)));
            } else {
                return notFound().build();
            }
        } catch (RuntimeException ex) {
            logger.warn("Error while verifying user session.", ex);
            return badRequest().body(toJson(ERROR_MESSAGE));
        }
    }

    @ResponseBody
    @RequestMapping(method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody String body) {
        try {
            final LoginJsonForm loginJsonForm = fromJson(body, LoginJsonForm.class);
            final Authentication authenticated = authenticationProvider.authenticate(loginJsonForm.getAuthentication());
            if (authenticated != null && authenticated.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticated);
                applicationEventPublisher.publishEvent(new AuthenticationSuccessEvent(authenticated));
                final SessionUser sessionUser = (SessionUser) authenticated.getPrincipal();
                return ok(toJson(of("userId", sessionUser.getUserId())));
            }
        } catch (RuntimeException ex) {
            logger.warn("Error during logging user with rq {}.", body, ex);
            return badRequest().body(toJson(ERROR_MESSAGE));
        }
        return badRequest().body(toJson(ERROR_MESSAGE));
    }

    @ResponseBody
    @RequestMapping(method = DELETE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity logout(@AuthenticationPrincipal SessionUser currentUser, HttpServletRequest httpRequest) {
        try {
            final HttpSession session = httpRequest.getSession(false);
            if (session != null) {
                session.invalidate();
            }

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(null);
            SecurityContextHolder.clearContext();

            return ok("");
        } catch (RuntimeException ex) {
            logger.warn("Error while logging out user {}.", currentUser.getUserId(), ex);
            return badRequest().body(toJson(ERROR_MESSAGE));
        }
    }

    static class UserJson {

        private final long userId;

        UserJson(SessionUser user) {
            this.userId = user.getUserId();
        }
    }

    static class ErrorMessage {

        private final String code;
        private final String message;

        ErrorMessage(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    static class LoginJsonForm {

        private final String username;
        private final String password;

        LoginJsonForm(String username, String password) {
            this.username = username;
            this.password = password;
        }

        UsernamePasswordAuthenticationToken getAuthentication() {
            return new UsernamePasswordAuthenticationToken(username, password);
        }
    }
}
