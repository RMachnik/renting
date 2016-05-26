package rent.rest.security.auth;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import rent.domain.security.SessionUser;
import rent.repo.api.user.AuthRepository;
import rent.repo.api.user.UserDto;

import java.util.List;

import static java.util.Collections.singletonList;

public class AuthenticationProviderImpl implements AuthenticationProvider {

    static final String PROTECTED = "PROTECTED";
    static final List<SimpleGrantedAuthority> USER_ROLES = singletonList(new SimpleGrantedAuthority(UserRole.USER.getRole()));

    private final AuthRepository authRepository;
    private final Logger logger;

    public AuthenticationProviderImpl(AuthRepository authRepository) {
        this.logger = LoggerFactory.getLogger(AuthenticationProviderImpl.class);
        this.authRepository = authRepository;
    }

    AuthenticationProviderImpl(AuthRepository authRepository, Logger logger) {
        this.logger = logger;
        this.authRepository = authRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            validate(authentication);
            String userName = authentication.getName();
            String password = authentication.getCredentials().toString();
            UserDto authenticatedUser = authRepository.authenticate(userName, password);
            if (authenticatedUser != null) {
                logger.info("{} is authenticated.", userName);
                return new UsernamePasswordAuthenticationToken(new SessionUser(authenticatedUser), PROTECTED, USER_ROLES);
            } else {
                logger.info("Unable to authenticate user: {}.", userName);
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
        if (StringUtils.isBlank(authentication.getName()) ||
                authentication.getCredentials() != null && StringUtils.isBlank(authentication.getCredentials().toString())) {
            throw new RuntimeException("Authentication validation failed.");
        }
    }
}
