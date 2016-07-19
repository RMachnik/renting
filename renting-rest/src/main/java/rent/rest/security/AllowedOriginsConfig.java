package rent.rest.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
public class AllowedOriginsConfig {

    private static final Logger logger = LoggerFactory.getLogger(AllowedOriginsConfig.class);

    @Value("${rest.allowed.origins:@null}")
    String allowedOrigins;

    @Bean
    CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", getAllowedOriginsConfiguration());
        return new CorsFilter(source);
    }

    private CorsConfiguration getAllowedOriginsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();

        addAllowedOrigins(config);
        config.setAllowedHeaders(asList("x-requested-with", "content-type"));
        config.setAllowCredentials(true);
        config.addAllowedMethod(GET);
        config.addAllowedMethod(PUT);
        config.addAllowedMethod(POST);
        config.addAllowedMethod(DELETE);
        config.addAllowedMethod(OPTIONS);
        return config;
    }

    private void addAllowedOrigins(CorsConfiguration config) {
        final String[] allowed = allowedOrigins.split(",");
        Stream.of(allowed).forEach(origin -> config.addAllowedOrigin(origin));
        logger.info("Configured allowed origins: {}", allowedOrigins);
    }
}
