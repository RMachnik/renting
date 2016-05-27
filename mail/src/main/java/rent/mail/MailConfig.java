package rent.mail;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@PropertySource("classpath:mail.properties")
public class MailConfig {

    @Bean
    MailService mailService() {
        return (email, body) -> System.out.println("EMAIL SEND");
    }

}
