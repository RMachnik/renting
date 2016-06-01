package rent.domain.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import rent.repo.api.Repositories;
import rent.repo.api.user.ActivationDetailsDto;
import rent.repo.api.user.ActivationRepository;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static rent.repo.stationary.user.StaticActivationDto.ACTIVATION_DETAILS_DTO;
import static rent.repo.stationary.user.StaticActivationDto.ACTIVATION_DTO;
import static rent.repo.stationary.user.StaticUserDto.USER_DTO;

@RunWith(MockitoJUnitRunner.class)
public class ActivationTest {

    @Mock
    ActivationRepository activationRepository;
    @Mock
    Repositories repositories;

    @Before
    public void setUp() {
        when(repositories.getUserActivationRepository()).thenReturn(activationRepository);
        when(activationRepository.getActivationDetails(ACTIVATION_DTO)).thenReturn(ACTIVATION_DETAILS_DTO);

    }

    @Test
    public void shouldGetActivationBasedOnToken() {
        Activation activation = new Activation(ACTIVATION_DTO, repositories);

        assertThat(activation.getUserId()).isEqualTo(USER_DTO.getId());
        assertThat(activation.getEmail()).isEqualTo(new Email(USER_DTO.getEmail()));
        assertThat(activation.getActivationToken()).isEqualTo(ACTIVATION_DTO.getActivationToken());
    }

    @Test
    public void shouldActivateUser() {
        Activation activation = new Activation(ACTIVATION_DTO, repositories);
        activation.activateAccount();

        verify(activationRepository).activateAccount(ACTIVATION_DTO);
    }

    @Test
    public void shouldSendActivationEmail() {
        Activation activation = new Activation(USER_DTO.getId(), USER_DTO.getEmail(), repositories);
        activation.sendActivationEmail();

        verify(activationRepository).sendActivationEmail(Mockito.argThat(new ArgumentMatcher<ActivationDetailsDto>() {
            @Override
            public boolean matches(Object argument) {
                ActivationDetailsDto activationDetailsDto = (ActivationDetailsDto) argument;
                return activation.getActivationToken().equals(activationDetailsDto.getActivationToken())
                        && activation.getUserId() == activationDetailsDto.getUserId()
                        && activation.getEmail().getAddress().equals(activationDetailsDto.getEmail());
            }
        }));
    }
}
