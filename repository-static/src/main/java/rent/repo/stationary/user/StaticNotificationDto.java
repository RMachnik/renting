package rent.repo.stationary.user;

import rent.repo.api.user.NotificationPreferenceDto;

public class StaticNotificationDto {
    public static final NotificationPreferenceDto NOTIFICATION_DTO = new NotificationPreferenceDto() {
        @Override
        public String getType() {
            return "INVOICE";
        }

        @Override
        public boolean isActive() {
            return true;
        }

        @Override
        public String getTemplate() {
            return "";
        }
    };
}
