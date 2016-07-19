package rent.repo.stationary;

import rent.repo.api.Repositories;
import rent.repo.api.renting.RentingRepository;
import rent.repo.api.user.ActivationRepository;
import rent.repo.api.user.NotificationPreferenceRepository;
import rent.repo.api.user.UserRepository;
import rent.repo.stationary.renting.StaticRentingRepo;
import rent.repo.stationary.user.StaticNotificationPrefRepo;
import rent.repo.stationary.user.StaticUserRepo;

public class StaticRepositories implements Repositories {

    StaticUserRepo staticUserRepo = new StaticUserRepo();
    StaticActivationRepo staticActivationRepo = new StaticActivationRepo();
    StaticRentingRepo staticRentingRepo = new StaticRentingRepo();
    StaticNotificationPrefRepo staticNotificationRepo = new StaticNotificationPrefRepo();

    @Override
    public UserRepository getUserRepository() {
        return staticUserRepo;
    }

    @Override
    public RentingRepository getRentingRepository() {
        return staticRentingRepo;
    }

    @Override
    public ActivationRepository getUserActivationRepository() {
        return staticActivationRepo;
    }

    @Override
    public NotificationPreferenceRepository getNotificationPreferenceRepository() {
        return staticNotificationRepo;
    }
}
