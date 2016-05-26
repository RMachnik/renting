package rent.repo.db.user;

import org.springframework.data.repository.CrudRepository;
import rent.repo.api.user.ActivationDetailsDto;

public interface ActivationCrudRepo extends CrudRepository<ActivationEntity, Long> {

    ActivationDetailsDto findActivationByActivationToken(String activationToken);

}
