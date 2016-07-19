package rent.rest.controller.util;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.http.ContentType.JSON;

public class RestAssuredSpec {

    public static RequestSpecification getSpec(int port) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        return builder.setPort(port)
                .setContentType(JSON)
                .build();
    }

}
