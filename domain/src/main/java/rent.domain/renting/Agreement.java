package rent.domain.renting;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import rent.repo.api.renting.AgreementDto;

@Getter
@ToString
@EqualsAndHashCode
public class Agreement {

    private long userId;
    private String agreementId;

    public Agreement(AgreementDto agreementDto) {
        this.userId = agreementDto.getUserId();
        this.agreementId = agreementDto.getAgreementId();
    }
}
