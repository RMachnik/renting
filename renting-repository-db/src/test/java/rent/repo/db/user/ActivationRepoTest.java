package rent.repo.db.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rent.mail.MailService;
import rent.repo.api.user.ActivationDetailsDto;
import rent.repo.api.user.ActivationRepository;
import rent.repo.db.RepoDbConfig;
import rent.repo.db.TestConfig;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static rent.repo.stationary.user.StaticActivationDto.ACTIVATION_DETAILS_DTO;
import static rent.repo.stationary.user.StaticActivationDto.ACTIVATION_DTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
        RepoDbConfig.class,
        TestConfig.class,
})
@ActiveProfiles("test")
public class ActivationRepoTest {

    @Autowired
    ActivationRepository activationRepo;
    @Autowired
    MailService mailService;

    @Test
    public void shouldSendEmailAndSavaActivationEntity() {
        activationRepo
                .sendActivationEmail(ACTIVATION_DETAILS_DTO);

        final ActivationDetailsDto activationByActivationToken = activationRepo
                .getActivationDetails(ACTIVATION_DTO);

        verify(mailService).sendEmail(ACTIVATION_DETAILS_DTO.getEmail(), ACTIVATION_DETAILS_DTO.getActivationToken());
        assertThat(activationByActivationToken.getId()).isPositive();
        assertThat(activationByActivationToken.getUserId()).isEqualTo(ACTIVATION_DETAILS_DTO.getUserId());
        assertThat(activationByActivationToken.getEmail()).isEqualTo(ACTIVATION_DETAILS_DTO.getEmail());
    }
}
