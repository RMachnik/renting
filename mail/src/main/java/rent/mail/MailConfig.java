package rent.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import rent.mail.impl.MailServiceImpl;

@Slf4j
@Configuration
@EnableAutoConfiguration
@ComponentScan
@PropertySource("classpath:mail.properties")
public class MailConfig {

    @Bean
    MailService mailService(JavaMailSender javaMailSender) {
        return new MailServiceImpl(javaMailSender);
    }

}
