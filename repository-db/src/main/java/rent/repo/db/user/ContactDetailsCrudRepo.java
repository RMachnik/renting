package rent.repo.db.user;

import org.springframework.data.repository.CrudRepository;
import rent.repo.api.user.ContactDetailsDto;
import rent.repo.db.user.entity.ContactDetailsEntity;

public interface ContactDetailsCrudRepo extends CrudRepository<ContactDetailsEntity, Long> {
    ContactDetailsDto findByUserId(long userId);
}
