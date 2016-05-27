package rent.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import rent.repo.db.RepoDbConfig;
import rent.rest.RestConfig;

@Configuration
@ComponentScan
@Import(value = {RepoDbConfig.class, RestConfig.class})
@PropertySource("classpath:core.properties")
public class CoreConfig {

}
