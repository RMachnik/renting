package rent.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import rent.repo.api.user.UserDto;
import rent.repo.db.user.UserCrudRepo;
import rent.repo.db.user.entity.UserEntity;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Renting {

    public static void main(final String[] args) {
        new SpringApplicationBuilder(Renting.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    @Autowired
    UserCrudRepo userCrudRepo;

    @Bean
    CommandLineRunner initTestData() {
        return args -> userCrudRepo.save(new UserEntity(new UserDto() {
            @Override
            public long getId() {
                return 0;
            }

            @Override
            public String getPassword() {
                return "password";
            }

            @Override
            public String getFirstName() {
                return "username";
            }

            @Override
            public String getLastName() {
                return "lastname";
            }

            @Override
            public String getEmail() {
                return "test@email.com";
            }

            @Override
            public boolean isActive() {
                return true;
            }
        }));
    }
}
