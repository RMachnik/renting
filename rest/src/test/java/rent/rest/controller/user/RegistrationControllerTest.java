package rent.rest.controller.user;

import com.jayway.restassured.filter.session.SessionFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rent.rest.RestConfig;
import rent.rest.controller.TestConfig;
import rent.rest.controller.util.RestEndpoint;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static java.lang.String.format;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static rent.common.util.StringUtil.j;
import static rent.repo.stationary.user.StaticUserDto.USER_DTO;
import static rent.rest.controller.util.RestAssuredSpec.getSpec;

@SpringApplicationConfiguration(classes = {
        RestConfig.class,
        TestConfig.class
})
@ActiveProfiles("test")
@WebIntegrationTest(randomPort = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class RegistrationControllerTest {

    private static final String REGISTRATION_JSON = j("{" +
            "  'username':'%s'," +
            "  'password':'%s'," +
            "  'email':'%s'" +
            "}");

    @Value("${local.server.port}")
    protected int port;

    @Autowired
    private RestEndpoint url;

    @Test
    public void shouldRegisterUser() {

        SessionFilter sessionFilter = new SessionFilter();

        given()
                .spec(getSpec(port))
                .filter(sessionFilter)
                .when()
                .body(format(REGISTRATION_JSON,
                        USER_DTO.getUserName(),
                        USER_DTO.getPassword(),
                        USER_DTO.getEmail())
                )
                .post(url.register)
                .then()
                .statusCode(SC_OK)
                .contentType(JSON);
    }

    @Test
    public void shouldNotRegisterUserWhenThereIsInvalidJson() {

        SessionFilter sessionFilter = new SessionFilter();

        given()
                .spec(getSpec(port))
                .filter(sessionFilter)
                .when()
                .body("INVALID")
                .post(url.register)
                .then()
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    public void shouldNotRegisterUserWhenEmailIsMalformed() {
        SessionFilter sessionFilter = new SessionFilter();

        given()
                .spec(getSpec(port))
                .filter(sessionFilter)
                .when()
                .body(format(REGISTRATION_JSON,
                        USER_DTO.getUserName(),
                        USER_DTO.getPassword(),
                        "malformedEMAIL")
                )
                .post(url.register)
                .then()
                .statusCode(SC_BAD_REQUEST);
    }
}
