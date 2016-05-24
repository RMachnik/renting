package rent.repo.db;

import rent.repo.api.Repositories;
import rent.repo.api.user.UserRepository;

public class DbRepositories implements Repositories {

    private final UserRepository userRepository;

    public DbRepositories(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }
}
