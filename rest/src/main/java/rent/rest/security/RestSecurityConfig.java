package rent.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.BootGlobalAuthenticationConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityDataConfiguration;
import org.springframework.boot.autoconfigure.security.SpringBootWebSecurityConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import rent.repo.api.Repositories;
import rent.repo.db.RepoDbConfig;
import rent.rest.security.auth.AuthProviderImpl;
import rent.rest.security.auth.UserRole;

@Configuration
@Import(value = {SpringBootWebSecurityConfiguration.class,
        BootGlobalAuthenticationConfiguration.class,
        SecurityDataConfiguration.class,
        AllowedOriginsConfig.class,
        RepoDbConfig.class
})
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${rest.http.enabled:true}")
    boolean securityEnabled;

    @Autowired
    Repositories repositories;

    @Bean
    CsrfTokenRepository csrfTokenRepository() {
        return new HttpSessionCsrfTokenRepository();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        return new AuthProviderImpl(repositories.getUserRepository());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        if (securityEnabled) {
            auth.authenticationProvider(authenticationProvider());
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (securityEnabled) {
            http
                    .authorizeRequests()
                    .regexMatchers("^/login\\?expired$").permitAll()
                    .regexMatchers("/healthcheck").permitAll()
                    .regexMatchers("/auth").permitAll()
                    .regexMatchers("/register").permitAll()
                    .regexMatchers("/conf").permitAll()
                    .anyRequest().hasRole(UserRole.USER.name())
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                    .exceptionHandling()
                    .and()
                    .logout()
                    .permitAll()
                    .and()
                    .csrf().disable();
        }
    }
}
