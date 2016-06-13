package rent.repo.stationary.renting;

import rent.repo.api.renting.AgreementDto;
import rent.repo.api.renting.RentingRepository;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static rent.repo.stationary.user.StaticUserRepo.USER_DTO;

public class StaticRentingRepo implements RentingRepository {

    public static final AgreementDto AGREEMNET_DTO = new AgreementDto() {
        @Override
        public String getAgreementId() {
            return "15-05-2016/apartmentId";
        }

        @Override
        public long getUserId() {
            return USER_DTO.getId();
        }
    };
    public static final List<AgreementDto> AGREEMENTS = newArrayList(AGREEMNET_DTO);

    @Override
    public List<AgreementDto> getAgreements(long userId) {
        return AGREEMENTS;
    }
}
