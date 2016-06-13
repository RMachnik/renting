package rent.domain.renting;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static rent.repo.stationary.renting.StaticRentingRepo.AGREEMNET_DTO;

public class AgreementTest {

    @Test
    public void shouldFulfillBasicProps() {
        Agreement agreement = new Agreement(AGREEMNET_DTO);

        assertThat(agreement.getUserId()).isEqualTo(AGREEMNET_DTO.getUserId());
        assertThat(agreement.getAgreementId()).isEqualTo(AGREEMNET_DTO.getAgreementId());
    }
}
