package security.auth;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import rent.domain.security.SessionUser;
import rent.repo.api.user.UserAuthenticationDto;
import rent.repo.api.user.UserAuthenticationRepository;

import java.util.List;

import static java.util.Collections.singletonList;

public class AuthenticationProviderImpl implements AuthenticationProvider {

    static final String PROTECTED = "PROTECTED";
    static final List<SimpleGrantedAuthority> USER_ROLES = singletonList(new SimpleGrantedAuthority(UserRole.USER.getRole()));

    private final UserAuthenticationRepository userAuthenticationRepository;
    private final Logger logger;

    public AuthenticationProviderImpl(UserAuthenticationRepository userAuthenticationRepository) {
        this.logger = LoggerFactory.getLogger(AuthenticationProviderImpl.class);
        this.userAuthenticationRepository = userAuthenticationRepository;
    }

    AuthenticationProviderImpl(UserAuthenticationRepository userAuthenticationRepository, Logger logger) {
        this.logger = logger;
        this.userAuthenticationRepository = userAuthenticationRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            validate(authentication);
            String userName = authentication.getName();
            String password = authentication.getCredentials().toString();
            UserAuthenticationDto authenticatedUser = userAuthenticationRepository.getUserAuthentication(userName, password);
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
