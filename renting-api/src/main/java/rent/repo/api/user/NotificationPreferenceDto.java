package rent.repo.api.user;

public interface NotificationPreferenceDto {

    String getType();

    boolean isActive();

    String getTemplate();
}
