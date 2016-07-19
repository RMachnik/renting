package rent.repo.db.user;

import org.springframework.data.repository.CrudRepository;
import rent.repo.api.user.InvoiceContactDetailsDto;
import rent.repo.db.user.entity.InvoiceContactDetailsEntity;

public interface InvoiceContactDetailsCrudRepo extends CrudRepository<InvoiceContactDetailsEntity, Long> {
    InvoiceContactDetailsDto findByUserId(long userId);
}
