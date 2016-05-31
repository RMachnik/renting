package rent.repo.db.user.entity;

import rent.repo.api.user.UserDto;
import rent.rest.api.RegistrationDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserEntity implements UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private boolean active;

    public UserEntity() {
    }

    public UserEntity(UserDto userDto) {
        id = userDto.getId();
        firstName = userDto.getFirstName();
        lastName = userDto.getLastName();
        password = userDto.getPassword();
        email = userDto.getEmail();
        active = userDto.isActive();
    }

    public UserEntity(RegistrationDto registrationDto) {
        firstName = registrationDto.getFirstName();
        password = registrationDto.getPassword();
        email = registrationDto.getEmail();
        active = false;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
