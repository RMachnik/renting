package rent.repo.db.user;

import rent.mail.MailService;
import rent.repo.api.user.ActivationDetailsDto;
import rent.repo.api.user.ActivationRepository;
import rent.repo.api.user.UserRepository;

public class ActivationRepo implements ActivationRepository {

    private final MailService mailService;
    private final ActivationCrudRepo activationCrudRepo;
    private final UserRepository userRepository;

    public ActivationRepo(MailService mailService,
                          ActivationCrudRepo activationCrudRepo,
                          UserRepository userRepository) {
        this.mailService = mailService;
        this.activationCrudRepo = activationCrudRepo;
        this.userRepository = userRepository;

    }

    @Override
    public void sendActivationEmail(String email, String token) {
        mailService.setdEmail(email, token);
    }

    @Override
    public void activateAccount(String activationToken) {
        final ActivationDetailsDto activationDetailsDto = activationCrudRepo.findActivationByActivationToken(activationToken);
        if (activationToken != null) {
            activationCrudRepo.delete(activationDetailsDto.getId());
        }
        userRepository.activateUser(activationDetailsDto.getUserId());
    }

    @Override
    public ActivationDetailsDto getActivationDetails(String activationToken) {
        return activationCrudRepo.findActivationByActivationToken(activationToken);
    }
}
