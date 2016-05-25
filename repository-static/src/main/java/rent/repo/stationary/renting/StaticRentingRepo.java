package rent.repo.stationary.renting;

import rent.repo.api.renting.AgreementDto;
import rent.repo.api.renting.RentingRepository;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static rent.repo.stationary.renting.StaticAgreementDto.AGREEMNET_DTO;

public class StaticRentingRepo implements RentingRepository {

    public static final List<AgreementDto> AGREEMENTS = newArrayList(AGREEMNET_DTO);

    @Override
    public List<AgreementDto> getAgreements(long userId) {
        return AGREEMENTS;
    }
}
