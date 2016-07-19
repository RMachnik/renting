package rent.rest.controller.util;

import static java.lang.String.format;

public class RestEndpoint {

    public final int port;
    public final String root;

    // unversioned
    public final String invalid;
    public final String login;
    public final String auth;
    public final String activate;
    public final String register;
    public final String test;

    public RestEndpoint(int port) {

        this.port = port;
        this.root = "http://localhost:" + port;

        login = "/login";
        auth = "/auth";
        register = "/register";
        activate = "/activate/%s";
        invalid = "/invalid";
        test = "/test";
    }

    public String activate(String token) {
        return format(activate, token);
    }
}
