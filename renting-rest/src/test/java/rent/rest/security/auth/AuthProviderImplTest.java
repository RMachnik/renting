package rent.rest.security.auth;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import rent.repo.api.Repositories;
import rent.repo.stationary.StaticRepositories;
import rent.rest.api.SessionUser;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.contains;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static rent.repo.stationary.user.StaticUserRepo.USER_DTO;

@RunWith(MockitoJUnitRunner.class)
public class AuthProviderImplTest {

    static final String EMAIL = USER_DTO.getEmail();
    static final String PASSWORD = USER_DTO.getPassword();

    AuthProviderImpl authenticationProvider;

    Repositories repositories = new StaticRepositories();

    @Mock
    Logger logger;

    @Before
    public void setUp() {
        authenticationProvider = new AuthProviderImpl(repositories, logger);
    }

    @Test
    public void simpleConstructor() {
        AuthenticationProvider authenticationProvider = new AuthProviderImpl(repositories);
        Authentication authMock = mock(Authentication.class);
        when(authMock.getName()).thenReturn(EMAIL);
        when(authMock.getCredentials()).thenReturn(PASSWORD);

        Authentication authenticated = authenticationProvider.authenticate(authMock);

        assertThat(authenticated).isNotNull();
        assertThat(authenticated.getPrincipal()).isEqualTo(new SessionUser(USER_DTO.getId()));
        assertThat(authenticated.getCredentials()).isEqualTo(AuthProviderImpl.PROTECTED);
    }

    @Test
    public void successfulAuthentication() {
        Authentication authMock = mock(Authentication.class);
        when(authMock.getName()).thenReturn(EMAIL);
        when(authMock.getCredentials()).thenReturn(PASSWORD);

        Authentication authenticated = authenticationProvider.authenticate(authMock);

        assertThat(authenticated).isNotNull();
        assertThat(authenticated.getPrincipal()).isEqualTo(new SessionUser(USER_DTO.getId()));
        assertThat(authenticated.getCredentials()).isEqualTo(AuthProviderImpl.PROTECTED);
        verify(logger, only()).info(contains("{} is authenticated."), eq(EMAIL));
    }

    @Test
    public void nooAuthentication() {
        Authentication authMock = mock(Authentication.class);
        when(authMock.getPrincipal()).thenReturn(EMPTY);
        when(authMock.getName()).thenReturn(EMAIL);
        when(authMock.getCredentials()).thenReturn(PASSWORD + "fake");

        Authentication authenticated = authenticationProvider.authenticate(authMock);

        assertThat(authenticated).isNull();
    }

    @Test
    public void supportedMethodsOfAuthentication() {
        assertThat(authenticationProvider.supports(UsernamePasswordAuthenticationToken.class)).isTrue();
        assertThat(authenticationProvider.supports(UsernamePasswordCredentials.class)).isFalse();
    }

    @Test
    public void shouldValidateUserCredentials() throws Exception {
        Authentication authMock = mock(Authentication.class);
        when(authMock.getPrincipal()).thenReturn(EMPTY);
        when(authMock.getName()).thenReturn(null);
        when(authMock.getCredentials()).thenReturn(null);

        Authentication authenticated = authenticationProvider.authenticate(authMock);

        assertThat(authenticated).isNull();
    }

    @Test
    public void shouldNotAuthenticateWhenRepoThrowException() throws Exception {
        Authentication authMock = mock(Authentication.class);
        when(authMock.getPrincipal()).thenReturn(EMPTY);
        when(authMock.getName()).thenReturn(EMAIL);
        when(authMock.getCredentials()).thenReturn(PASSWORD + "1235");

        Authentication authenticated = authenticationProvider.authenticate(authMock);

        assertThat(authenticated).isNull();
    }
}
