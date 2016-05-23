package rent.domain.user;

public class User {
    
    private long id;
    private String login;
    private String password;
    private Email email;


    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Email getEmail() {
        return email;
    }
}
