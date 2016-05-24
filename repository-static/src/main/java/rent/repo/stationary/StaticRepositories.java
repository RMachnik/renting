package rent.repo.stationary;

import rent.repo.api.Repositories;
import rent.repo.api.user.UserRepository;
import rent.repo.stationary.user.StaticUserRepository;

public class StaticRepositories implements Repositories {

    StaticUserRepository staticUserRepository = new StaticUserRepository();

    @Override
    public UserRepository getUserRepository() {
        return staticUserRepository;
    }
}
