package rent.repo.db;

import rent.repo.api.Repositories;
import rent.repo.api.renting.RentingRepository;
import rent.repo.api.user.ActivationRepository;
import rent.repo.api.user.UserRepository;

public class DbRepositories implements Repositories {

    private final UserRepository userRepository;
    private final ActivationRepository activationRepository;

    public DbRepositories(UserRepository userRepository,
                          ActivationRepository activationRepository) {
        this.userRepository = userRepository;
        this.activationRepository = activationRepository;
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
}
