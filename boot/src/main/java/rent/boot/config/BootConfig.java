package rent.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import rent.core.CoreConfig;

@Configuration
@Import(CoreConfig.class)
public class BootConfig {
}
