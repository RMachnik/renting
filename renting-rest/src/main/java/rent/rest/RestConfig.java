package rent.rest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import rent.rest.security.RestSecurityConfig;

@Configuration
@ComponentScan
@Import(RestSecurityConfig.class)
@PropertySource(value = {"classpath:rest.properties"})
public class RestConfig {
}
