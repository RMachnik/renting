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
import rent.common.ValidationException;
import rent.repo.api.user.AuthRepository;
import rent.rest.api.SessionUser;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.contains;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static rent.repo.stationary.user.StaticUserDto.USER_DTO;

@RunWith(MockitoJUnitRunner.class)
public class AuthProviderImplTest {

    static final String PASSWORD = "password";
    static final String USER_NAME = "testUser";

    AuthProviderImpl authenticationProvider;

    @Mock
    AuthRepository authRepository;

    @Mock
    Logger logger;

    @Before
    public void setUp() {
        authenticationProvider = new AuthProviderImpl(authRepository, logger);
    }

    @Test
    public void simpleConstructor() {
        AuthenticationProvider authenticationProvider = new AuthProviderImpl(authRepository);
        Authentication authMock = mock(Authentication.class);
        when(authMock.getName()).thenReturn(USER_NAME);
        when(authMock.getCredentials()).thenReturn(PASSWORD);
        when(authRepository.authenticate(USER_NAME, PASSWORD)).thenReturn(USER_DTO);

        Authentication authenticated = authenticationProvider.authenticate(authMock);

        assertThat(authenticated).isNotNull();
        assertThat(authenticated.getPrincipal()).isEqualTo(new SessionUser(USER_DTO.getId()));
        assertThat(authenticated.getCredentials()).isEqualTo(AuthProviderImpl.PROTECTED);
    }

    @Test
    public void successfulAuthentication() {
        Authentication authMock = mock(Authentication.class);
        when(authMock.getName()).thenReturn(USER_NAME);
        when(authMock.getCredentials()).thenReturn(PASSWORD);
        when(authRepository.authenticate(USER_NAME, PASSWORD)).thenReturn(USER_DTO);

        Authentication authenticated = authenticationProvider.authenticate(authMock);

        assertThat(authenticated).isNotNull();
        assertThat(authenticated.getPrincipal()).isEqualTo(new SessionUser(USER_DTO.getId()));
        assertThat(authenticated.getCredentials()).isEqualTo(AuthProviderImpl.PROTECTED);
        verify(logger, only()).info(contains("{} is authenticated."), eq(USER_NAME));
    }

    @Test
    public void nooAuthentication() {
        Authentication authMock = mock(Authentication.class);
        when(authMock.getPrincipal()).thenReturn(EMPTY);
        when(authMock.getName()).thenReturn(USER_NAME);
        when(authMock.getCredentials()).thenReturn(PASSWORD);

        Authentication authenticated = authenticationProvider.authenticate(authMock);

        assertThat(authenticated).isNull();
        verify(logger, only()).info(contains("Unable to authenticate user: {}."), eq(USER_NAME));
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
        when(authMock.getName()).thenReturn(USER_NAME);
        when(authMock.getCredentials()).thenReturn(PASSWORD);
        doThrow(ValidationException.class).when(authRepository).authenticate(USER_NAME, PASSWORD);

        Authentication authenticated = authenticationProvider.authenticate(authMock);

        assertThat(authenticated).isNull();
    }
}
