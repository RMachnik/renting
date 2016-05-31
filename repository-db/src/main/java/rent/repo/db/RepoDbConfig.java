package rent.repo.db;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import rent.mail.MailConfig;
import rent.mail.MailService;
import rent.repo.api.Repositories;
import rent.repo.api.user.ActivationRepository;
import rent.repo.api.user.UserRepository;
import rent.repo.db.user.*;

@Configuration
@ComponentScan
@Import(MailConfig.class)
@EnableAutoConfiguration
@PropertySource("classpath:repo-db.properties")
public class RepoDbConfig {

    @Bean
    UserRepository userRepository(UserCrudRepo userCrudRepo, ContactDetailsCrudRepo contactDetailsCrudRepo) {
        return new UserRepoImpl(userCrudRepo, contactDetailsCrudRepo);
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
