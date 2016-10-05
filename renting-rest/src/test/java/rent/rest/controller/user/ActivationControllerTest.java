package rent.rest.controller.user;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import rent.domain.user.User;
import rent.repo.api.Repositories;
import rent.rest.RestConfig;
import rent.rest.controller.TestConfig;
import rent.rest.controller.util.RestEndpoint;

import static com.jayway.restassured.RestAssured.given;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static rent.repo.stationary.user.StaticActivationDto.ACTIVATION_DTO;
import static rent.repo.stationary.user.StaticRegistrationDto.REGISTRATION_DTO;
import static rent.rest.controller.util.RestAssuredSpec.getSpec;

@ContextConfiguration(classes = {
        RestConfig.class,
        TestConfig.class
})
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ActivationControllerTest {

    @Value("${local.server.port}")
    protected int port;
    @Autowired
    Repositories repositories;

    @Autowired
    private RestEndpoint url;

    @Before
    public void setUp() {
        new User(REGISTRATION_DTO, repositories);
    }

    @Test
    public void shouldRedirectToLoginPageWhenNotAuthenticated() {
        given()
                .spec(getSpec(port))
                .auth().none()
                .when()
                .get(url.activate(ACTIVATION_DTO.getActivationToken()))
                .then()
                .statusCode(SC_OK);
    }

}
