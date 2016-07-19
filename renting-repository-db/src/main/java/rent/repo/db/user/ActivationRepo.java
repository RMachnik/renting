package rent.repo.db.user;

import rent.mail.MailService;
import rent.repo.api.user.ActivationDetailsDto;
import rent.repo.api.user.ActivationDto;
import rent.repo.api.user.ActivationRepository;
import rent.repo.db.user.entity.ActivationEntity;

public class ActivationRepo implements ActivationRepository {

    private final MailService mailService;
    private final ActivationCrudRepo activationCrudRepo;

    public ActivationRepo(MailService mailService,
                          ActivationCrudRepo activationCrudRepo) {
        this.mailService = mailService;
        this.activationCrudRepo = activationCrudRepo;

    }

    @Override
    public void sendActivationEmail(ActivationDetailsDto activationDetailsDto) {
        mailService.sendEmail(activationDetailsDto.getEmail(), activationDetailsDto.getActivationToken());
        activationCrudRepo.save(new ActivationEntity(activationDetailsDto));
    }

    @Override
    public void activateAccount(ActivationDto activationDto) {
        final String activationToken = activationDto.getActivationToken();
        final ActivationDetailsDto activationDetailsDto = activationCrudRepo.findActivationByActivationToken(activationToken);
        if (activationToken != null) {
            activationCrudRepo.delete(activationDetailsDto.getId());
        }
    }

    @Override
    public ActivationDetailsDto getActivationDetails(ActivationDto activationDto) {
        return activationCrudRepo.findActivationByActivationToken(activationDto.getActivationToken());
    }

    @Override
    public void remove(String activationToken) {
        final ActivationDetailsDto activationByActivationToken = activationCrudRepo.findActivationByActivationToken(activationToken);
        activationCrudRepo.delete((ActivationEntity) activationByActivationToken);
    }
}
