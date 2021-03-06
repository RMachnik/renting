package rent.repo.db.user.entity;

import rent.repo.api.user.ActivationDetailsDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ActivationEntity implements ActivationDetailsDto {

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

    public ActivationEntity() {
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

    @Override
    public String getActivationToken() {
        return activationToken;
    }

}
