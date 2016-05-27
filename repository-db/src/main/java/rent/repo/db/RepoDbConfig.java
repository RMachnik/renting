package rent.repo.db;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import rent.mail.MailConfig;
import rent.mail.MailService;
import rent.repo.api.Repositories;
import rent.repo.api.user.ActivationRepository;
import rent.repo.api.user.UserRepository;
import rent.repo.db.user.ActivationCrudRepo;
import rent.repo.db.user.ActivationRepo;
import rent.repo.db.user.UserCrudRepo;
import rent.repo.db.user.UserRepoImpl;

@Configuration
@ComponentScan
@Import(MailConfig.class)
@EnableAutoConfiguration
@PropertySource("classpath:repo-db.properties")
public class RepoDbConfig {

    @Bean
    UserRepository userRepository(UserCrudRepo userCrudRepo) {
        return new UserRepoImpl(userCrudRepo);
    }

    @Bean
    ActivationRepository activationRepository(ActivationCrudRepo activationCrudRepo,
                                              UserRepository userRepository, MailService mailService
    ) {
        return new ActivationRepo(mailService,
                activationCrudRepo,
                userRepository);
    }

    @Bean
    Repositories dbRepositories(UserRepository userRepository, ActivationRepository activationRepository) {
        return new DbRepositories(userRepository,
                activationRepository);
    }

}
