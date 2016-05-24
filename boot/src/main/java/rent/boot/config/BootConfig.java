package rent.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import rent.repo.db.RepoDbConfig;
import rent.rest.RestConfig;

@Configuration
@Import({RestConfig.class, RepoDbConfig.class})
public class BootConfig {
}
