package rent.rest.security.auth;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rent.rest.api.ErrorMessage;
import rent.rest.api.LoginJsonForm;
import rent.rest.api.SessionUser;
import rent.rest.api.UserJson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.google.common.collect.ImmutableMultimap.of;
import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static rent.common.util.RestUtil.perform;
import static rent.common.util.SerializationUtil.fromJson;
import static rent.common.util.SerializationUtil.toJson;

@Log4j2
@RestController
@RequestMapping("/auth")
public class ApiAuthController {

    static final ErrorMessage ERROR_MESSAGE = new ErrorMessage("407", "Sorry, unable to authenticate.");

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
            log.warn("Error while verifying user session.", ex);
            return badRequest().body(toJson(ERROR_MESSAGE));
        }
    }

    @ResponseBody
    @RequestMapping(method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody String body) {
        return perform(() -> {
            final LoginJsonForm loginJsonForm = fromJson(body, LoginJsonForm.class);
            final Authentication authenticated = authenticationProvider.authenticate(loginJsonForm.getAuthentication());
            if (authenticated != null && authenticated.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticated);
                applicationEventPublisher.publishEvent(new AuthenticationSuccessEvent(authenticated));
                final SessionUser sessionUser = (SessionUser) authenticated.getPrincipal();
                return of("userId", sessionUser.getUserId());
            } else {
                throw new RuntimeException(format("Unable to login with rq: %s.", body));
            }
        }, ERROR_MESSAGE, log);
    }

    @ResponseBody
    @RequestMapping(method = DELETE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity logout(@AuthenticationPrincipal SessionUser currentUser, HttpServletRequest httpRequest) {
        return perform(() ->
                {
                    final HttpSession session = httpRequest.getSession(false);
                    if (session != null) {
                        session.invalidate();
                    }

                    SecurityContext context = SecurityContextHolder.getContext();
                    context.setAuthentication(null);
                    SecurityContextHolder.clearContext();
                    return "";
                },
                ERROR_MESSAGE,
                log);
    }
}
