package rent.repo.db.user;

import rent.repo.api.user.ActivationDetailsDto;
import rent.repo.api.user.ActivationDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ActivationEntity implements ActivationDto, ActivationDetailsDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;
    private String email;
    private String activationToken;

    public ActivationEntity(ActivationDetailsDto activationDetailsDto) {
        this.activationToken = activationDetailsDto.getActivationToken();
        this.userId = activationDetailsDto.getUserId();
        this.email = activationDetailsDto.getEmail();
    }

    @Override
    public String getActivationToken() {
        return activationToken;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public String getEmail() {
        return email;
    }

}
