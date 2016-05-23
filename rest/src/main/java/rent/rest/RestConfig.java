package rent.rest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import security.RestSecurityConfig;

@Configuration
@ComponentScan(basePackages = "rent.rest.controller")
@Import(RestSecurityConfig.class)
@PropertySource(value = {"classpath:rest.properties"})
public class RestConfig {
}
