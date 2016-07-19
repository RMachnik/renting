package rent.repo.api.renting;

import java.util.List;

public interface RentingRepository {

    List<AgreementDto> getAgreements(long userId);
}
