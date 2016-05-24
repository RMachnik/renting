package rent.rest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import rent.repo.api.Repositories;
import rent.repo.stationary.StaticRepositories;
import rent.rest.controller.util.RestEndpoint;

@Configuration
@EnableAutoConfiguration
public class TestConfig {


    @Value("${rest.test.username}")
    String username;

    @Value("${rest.test.password}")
    String pass;

    @Bean
    @Lazy
    RestEndpoint restURL(@Value("${local.server.port}") int port) {
        return new RestEndpoint(port);
    }

    @Bean
    @Profile("test")
    Repositories repositories() {
        return new StaticRepositories();
    }
}
