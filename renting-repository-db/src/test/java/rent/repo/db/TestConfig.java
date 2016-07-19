package rent.repo.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import rent.mail.MailService;

import static org.mockito.Mockito.mock;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    MailService mailService() {
        return mock(MailService.class);
    }
}
