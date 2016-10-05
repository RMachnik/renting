package rent.rest.controller.user;

import com.jayway.restassured.filter.session.SessionFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import rent.mail.MailService;
import rent.rest.RestConfig;
import rent.rest.controller.TestConfig;
import rent.rest.controller.util.RestEndpoint;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static java.lang.String.format;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static rent.common.util.StringUtil.j;
import static rent.repo.stationary.user.StaticUserRepo.USER_DTO;
import static rent.rest.controller.util.RestAssuredSpec.getSpec;

@ContextConfiguration(classes = {
        RestConfig.class,
        TestConfig.class
})
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@RunWith(SpringRunner.class)
public class RegistrationControllerTest {

    private static final String REGISTRATION_JSON = j("{" +
            "  'username':'%s'," +
            "  'password':'%s'," +
            "  'email':'%s'" +
            "}");

    @Value("${local.server.port}")
    protected int port;
    @Autowired
    MailService mailService;
    @Autowired
    private RestEndpoint url;

    @Before
    public void setUp() {
        reset(mailService);
    }

    @Test
    public void shouldRegisterUser() {

        SessionFilter sessionFilter = new SessionFilter();

        given()
                .spec(getSpec(port))
                .filter(sessionFilter)
                .when()
                .body(format(REGISTRATION_JSON,
                        USER_DTO.getFirstName(),
                        USER_DTO.getPassword(),
                        USER_DTO.getEmail())
                )
                .post(url.register)
                .then()
                .statusCode(SC_OK)
                .contentType(JSON);

        verify(mailService).sendEmail(Mockito.eq(USER_DTO.getEmail()), any());
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
                        USER_DTO.getFirstName(),
                        USER_DTO.getPassword(),
                        "malformedEMAIL")
                )
                .post(url.register)
                .then()
                .statusCode(SC_BAD_REQUEST);
    }
}
