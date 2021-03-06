package rent.rest.controller;

import com.jayway.restassured.filter.session.SessionFilter;
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
import rent.rest.controller.util.RestEndpoint;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static java.lang.String.format;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static rent.common.util.StringUtil.j;
import static rent.repo.stationary.user.StaticRegistrationDto.REGISTRATION_DTO;
import static rent.repo.stationary.user.StaticUserRepo.USER_DTO;
import static rent.rest.controller.util.RestAssuredSpec.getSpec;

@ContextConfiguration(classes = {
        RestConfig.class,
        TestConfig.class
})
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ApiAuthControllerTest {

    private static final String LOGIN_JSON = j("{ \n" +
            "  'email':'%s',\n" +
            "  'password':'%s'\n" +
            "}");

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
                .get(url.test)
                .then()
                .statusCode(SC_OK)
                .body(containsString("Username"));
    }

    @Test
    public void shouldReturnHttpStatus404WhenUserNotLoggedIn() {
        given()
                .spec(getSpec(port))
                .when()
                .get(url.auth)
                .then()
                .statusCode(SC_NOT_FOUND);
    }

    @Test
    public void shouldReturnLoggedInUserDetails() {

        SessionFilter sessionFilter = new SessionFilter();

        given()
                .spec(getSpec(port))
                .filter(sessionFilter)
                .when()
                .body(format(LOGIN_JSON, USER_DTO.getEmail(), USER_DTO.getPassword()))
                .post(url.auth)
                .then()
                .statusCode(SC_OK)
                .contentType(JSON);

        given()
                .spec(getSpec(port))
                .filter(sessionFilter)
                .get(url.auth)
                .then()
                .statusCode(SC_OK)
                .body(equalTo(j("{'userId':1}")));
    }

    @Test
    public void shouldPerformLoginAndGetSecuredPage() {
        SessionFilter sessionFilter = new SessionFilter();

        given()
                .spec(getSpec(port))
                .when()
                .body(format(LOGIN_JSON, USER_DTO.getEmail(), USER_DTO.getPassword()))
                .post(url.auth)
                .then()
                .statusCode(SC_OK)
                .contentType(JSON)
                .body(equalTo(j("{}")));

        given()
                .spec(getSpec(port))
                .filter(sessionFilter)
                .log().all()
                .get(url.root)
                .then()
                .statusCode(SC_OK);

    }

    @Test
    public void shouldReturnFailMessageOnLoginAndNotGetSecuredPage() {
        SessionFilter sessionFilter = new SessionFilter();

        given()
                .spec(getSpec(port))
                .filter(sessionFilter)
                .when()
                .body(format(LOGIN_JSON, USER_DTO.getEmail(), USER_DTO.getPassword() + "124456"))
                .post(url.auth)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .contentType(JSON)
                .body(equalTo(j("{'code':'407','message':'Sorry, unable to authenticate.'}")));

        given().filter(sessionFilter)
                .log().all()
                .get(url.root)
                .then()
                .statusCode(SC_OK)
                .contentType("text/html;charset=UTF-8")
                .body(containsString("login"));

    }

    @Test
    public void shouldPerformLogoutAndGetSecuredPage() {
        SessionFilter sessionFilter = new SessionFilter();

        given()
                .spec(getSpec(port))
                .filter(sessionFilter)
                .when()
                .body(format(LOGIN_JSON, USER_DTO.getEmail(), USER_DTO.getPassword()))
                .post(url.auth)
                .then()
                .statusCode(SC_OK)
                .contentType(JSON);

        given()
                .spec(getSpec(port))
                .filter(sessionFilter)
                .delete(url.auth)
                .then()
                .statusCode(SC_OK);

        given()
                .spec(getSpec(port))
                .filter(sessionFilter)
                .get(url.root)
                .then()
                .statusCode(SC_OK)
                .contentType("text/html;charset=UTF-8")
                .body(containsString("login"));
    }


    @Test
    public void shouldReturnBadRequestWhenRqMalformed() {
        given()
                .spec(getSpec(port))
                .when()
                .body("malformed")
                .post(url.auth)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .contentType(JSON)
                .body(equalTo(j("{'code':'407','message':'Sorry, unable to authenticate.'}")));

    }
}
