package rent.rest.security.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import rent.repo.api.user.AuthRepository;
import rent.repo.api.user.UserDto;
import rent.rest.api.SessionUser;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static rent.rest.security.auth.UserRole.USER;

public class AuthProviderImpl implements AuthenticationProvider {

    static final String PROTECTED = "PROTECTED";
    static final List<SimpleGrantedAuthority> USER_ROLES = singletonList(new SimpleGrantedAuthority(USER.getRole()));

    private final AuthRepository authRepository;
    private final Logger logger;

    public AuthProviderImpl(AuthRepository authRepository) {
        this.logger = LoggerFactory.getLogger(AuthProviderImpl.class);
        this.authRepository = authRepository;
    }

    AuthProviderImpl(AuthRepository authRepository, Logger logger) {
        this.logger = logger;
        this.authRepository = authRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            validate(authentication);
            String email = authentication.getName();
            String password = authentication.getCredentials().toString();
            UserDto authenticatedUser = authRepository.authenticate(email, password);
            if (authenticatedUser != null) {
                logger.info("{} is authenticated.", email);
                return new UsernamePasswordAuthenticationToken(new SessionUser(authenticatedUser.getId()), PROTECTED, USER_ROLES);
            } else {
                logger.info("Unable to authenticate user: {}.", email);
                return null;
            }
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private void validate(Authentication authentication) {
        if (isBlank(authentication.getName()) ||
                authentication.getCredentials() != null && isBlank(authentication.getCredentials().toString())) {
            throw new RuntimeException("Authentication validation failed.");
        }
    }
}
