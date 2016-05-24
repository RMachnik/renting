package rent.repo.db.user;

import rent.repo.api.user.UserAuthenticationDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserAuthenticationEntity implements UserAuthenticationDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String userName;
    private String lastName;
    private String password;

    public UserAuthenticationEntity() {
    }

    public UserAuthenticationEntity(UserAuthenticationDto userAuthenticationDto) {
        id = userAuthenticationDto.getUserId();
        userName = userAuthenticationDto.getUserName();
        lastName = userAuthenticationDto.getLastName();
        password = userAuthenticationDto.getPassword();
    }

    @Override
    public long getUserId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
}