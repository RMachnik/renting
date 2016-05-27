package rent.repo.db.user;

import rent.mail.MailService;
import rent.repo.api.user.ActivationDetailsDto;
import rent.repo.api.user.ActivationDto;
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
    public void sendActivationEmail(ActivationDetailsDto activationDetailsDto) {
        mailService.setdEmail(activationDetailsDto.getEmail(), activationDetailsDto.getActivationToken());
    }

    @Override
    public ActivationDetailsDto getActivationDetails(ActivationDto activationDto) {
        return activationCrudRepo.findActivationByActivationToken(activationDto.getActivationToken());
    }

    @Override
    public void activateAccount(ActivationDto activationDto) {
        final String activationToken = activationDto.getActivationToken();
        final ActivationDetailsDto activationDetailsDto = activationCrudRepo.findActivationByActivationToken(activationToken);
        if (activationToken != null) {
            activationCrudRepo.delete(activationDetailsDto.getId());
        }
        userRepository.activateUser(activationDetailsDto.getUserId());
    }
}
