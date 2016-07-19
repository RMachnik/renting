package rent.repo.db;

import rent.repo.api.Repositories;
import rent.repo.api.renting.RentingRepository;
import rent.repo.api.user.ActivationRepository;
import rent.repo.api.user.NotificationPreferenceRepository;
import rent.repo.api.user.UserRepository;

public class DbRepositories implements Repositories {

    private final UserRepository userRepository;
    private final ActivationRepository activationRepository;
    private final NotificationPreferenceRepository notificationPreferenceRepository;

    public DbRepositories(UserRepository userRepository,
                          ActivationRepository activationRepository, NotificationPreferenceRepository notificationPreferenceRepository) {
        this.userRepository = userRepository;
        this.activationRepository = activationRepository;
        this.notificationPreferenceRepository = notificationPreferenceRepository;
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public RentingRepository getRentingRepository() {
        return null;
    }

    @Override
    public ActivationRepository getUserActivationRepository() {
        return activationRepository;
    }

    @Override
    public NotificationPreferenceRepository getNotificationPreferenceRepository() {
        return notificationPreferenceRepository;
    }
}
