package rent.repo.db.user;

import org.springframework.data.repository.CrudRepository;
import rent.repo.api.user.UserAuthenticationDto;

import java.util.List;

/**
 * This is example of repository.
 * To get most of spring-data we need to create similar things with our Entity classes that will extend DTO class.
 */
public interface UserCrudRepository extends CrudRepository<UserAuthenticationEntity, Long> {

    List<UserAuthenticationDto> findByUserName(String userName);
}
