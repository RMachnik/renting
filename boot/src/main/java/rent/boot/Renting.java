package rent.boot;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Renting {

    public static void main(final String[] args) {
        new SpringApplicationBuilder(Renting.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
