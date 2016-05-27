package rent.repo.db.user;

import org.springframework.data.repository.CrudRepository;
import rent.repo.api.user.ActivationDetailsDto;
import rent.repo.db.user.entity.ActivationEntity;

public interface ActivationCrudRepo extends CrudRepository<ActivationEntity, Long> {

    ActivationDetailsDto findActivationByActivationToken(String activationToken);

}
