package rent.domain.renting;

import org.junit.Test;
import rent.repo.stationary.StaticRepositories;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static rent.repo.stationary.renting.StaticRentingRepo.AGREEMNET_DTO;
import static rent.repo.stationary.user.StaticUserRepo.USER_DTO;

public class RentingTest {

    @Test
    public void shouldGetAllAgreements() {
        Renting renting = new Renting(USER_DTO.getId(), new StaticRepositories());

        final List<Agreement> agreements = renting.getRentingAgreement();

        assertThat(agreements).isNotEmpty();
        final Agreement firstAgreement = agreements.get(0);
        assertThat(firstAgreement.getAgreementId()).isEqualTo(AGREEMNET_DTO.getAgreementId());
        assertThat(firstAgreement.getUserId()).isEqualTo(AGREEMNET_DTO.getUserId());
    }
}
