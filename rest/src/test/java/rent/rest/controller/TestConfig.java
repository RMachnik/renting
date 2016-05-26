package rent.rest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import rent.rest.controller.util.RestEndpoint;

@Configuration
@EnableAutoConfiguration
public class TestConfig {

    @Bean
    @Lazy
    RestEndpoint restURL(@Value("${local.server.port}") int port) {
        return new RestEndpoint(port);
    }

}
