package rent.rest.controller.util;

public class RestEndpoint {

    public final int port;
    public final String root;

    // unversioned
    public final String invalid;
    public final String login;
    public final String auth;
    public final String test;

    public RestEndpoint(int port) {

        this.port = port;
        this.root = "http://localhost:" + port;

        login = "/login";
        auth = "/auth";
        invalid = "/invalid";
        test = "/test";
    }
}
