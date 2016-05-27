package rent.repo.stationary.renting;

import rent.repo.api.renting.AgreementDto;

import static rent.repo.stationary.user.StaticUserDto.USER_DTO;

public class StaticAgreementDto {

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
}

