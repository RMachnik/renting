package rent.repo.db;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import rent.repo.api.Repositories;
import rent.repo.api.user.UserRepository;
import rent.repo.db.user.UserCrudRepo;
import rent.repo.db.user.UserRepoImpl;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class RepoDbConfig {

    @Bean
    UserRepository userRepository(UserCrudRepo userCrudRepo) {
        return new UserRepoImpl(userCrudRepo);
    }

    @Bean
    Repositories dbRepositories(UserRepository userRepository) {
        return new DbRepositories(userRepository);
    }

}
