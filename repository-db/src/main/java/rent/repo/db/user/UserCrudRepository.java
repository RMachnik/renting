package rent.repo.db.user;

import org.springframework.data.repository.CrudRepository;
import rent.repo.api.user.SessionUserDto;

import java.util.List;

/**
 * This is example of repository.
 * To get most of spring-data we need to create similar things with our Entity classes that will extend DTO class.
 */
public interface UserCrudRepository extends CrudRepository<SessionUserEntity, Long> {

    List<SessionUserDto> findByUserName(String userName);

    List<SessionUserDto> findByUserNameAndPassword(String name, String userName);
}
