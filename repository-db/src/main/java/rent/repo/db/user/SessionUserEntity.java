package rent.repo.db.user;

import rent.repo.api.user.RegistrationDto;
import rent.repo.api.user.SessionUserDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SessionUserEntity implements SessionUserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String userName;
    private String lastName;
    private String password;
    private String email;

    public SessionUserEntity() {
    }

    public SessionUserEntity(SessionUserDto sessionUserDto) {
        id = sessionUserDto.getUserId();
        userName = sessionUserDto.getUserName();
        lastName = sessionUserDto.getLastName();
        password = sessionUserDto.getPassword();
        email = sessionUserDto.getEmail();
    }

    public SessionUserEntity(RegistrationDto registrationDto) {
        userName = registrationDto.getUserName();
        password = registrationDto.getPassword();
        email = registrationDto.getEmail();
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

    @Override
    public String getEmail() {
        return email;
    }
}
