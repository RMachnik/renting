package rent.repo.api;

import rent.repo.api.renting.RentingRepository;
import rent.repo.api.user.ActivationRepository;
import rent.repo.api.user.NotificationPreferenceRepository;
import rent.repo.api.user.UserRepository;

public interface Repositories {

    UserRepository getUserRepository();

    RentingRepository getRentingRepository();

    ActivationRepository getUserActivationRepository();

    NotificationPreferenceRepository getNotificationPreferenceRepository();
}
