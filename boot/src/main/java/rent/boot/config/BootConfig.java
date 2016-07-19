package rent.boot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import rent.repo.db.RepoDbConfig;
import rent.rest.RestConfig;

@Configuration
@ComponentScan
@Import(value = {RepoDbConfig.class, RestConfig.class})
public class BootConfig {
}
