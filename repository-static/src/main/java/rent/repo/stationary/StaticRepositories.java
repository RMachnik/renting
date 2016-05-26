package rent.repo.stationary;

import rent.repo.api.Repositories;
import rent.repo.api.renting.RentingRepository;
import rent.repo.api.user.ActivationRepository;
import rent.repo.api.user.UserRepository;
import rent.repo.stationary.renting.StaticRentingRepo;
import rent.repo.stationary.user.StaticUserRepository;

public class StaticRepositories implements Repositories {

    StaticUserRepository staticUserRepository = new StaticUserRepository();
    StaticRentingRepo staticRentingRepo = new StaticRentingRepo();

    @Override
    public UserRepository getUserRepository() {
        return staticUserRepository;
    }

    @Override
    public RentingRepository getRentingRepository() {
        return staticRentingRepo;
    }

    @Override
    public ActivationRepository getUserActivationRepository() {
        return null;
    }
}
