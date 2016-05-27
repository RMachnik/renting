package rent.repo.db.user;

import org.springframework.data.repository.CrudRepository;
import rent.repo.api.user.UserDto;
import rent.repo.db.user.entity.UserEntity;

import java.util.List;

/**
 * This is example of repository.
 * To get most of spring-data we need to create similar things with our Entity classes that will extend DTO class.
 */
public interface UserCrudRepo extends CrudRepository<UserEntity, Long> {

    List<UserDto> findByUserName(String userName);

    List<UserDto> findByUserNameAndPassword(String name, String userName);
}
