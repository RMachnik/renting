package rent.repo.db.user.entity;

import rent.repo.api.user.NotificationPreferenceDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NotificationPreferencesEntity implements NotificationPreferenceDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;
    private String template;
    private String type;
    private boolean active;

    public NotificationPreferencesEntity() {
    }

    public NotificationPreferencesEntity(long userId, NotificationPreferenceDto dto) {
        this.userId = userId;
        this.type = dto.getType();
        this.template = dto.getTemplate();
        this.active = dto.isActive();
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public String getTemplate() {
        return template;
    }
}
