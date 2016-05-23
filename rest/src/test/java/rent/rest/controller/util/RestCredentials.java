package rent.rest.controller.util;

public interface RestCredentials {

    String getUsername();

    String getPassword();

    static RestCredentials credentials(String username, String password) {
        return new RestCredentials() {
            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public String getPassword() {
                return password;
            }
        };
    }
}
