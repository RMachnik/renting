package rent.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import rent.rest.RestConfig;

@Configuration
@Import({RestConfig.class})
public class BootConfig {
}
