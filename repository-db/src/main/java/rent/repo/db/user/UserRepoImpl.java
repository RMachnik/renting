package rent.repo.db.user;

import rent.repo.api.user.ContactDetailsDto;
import rent.repo.api.user.InvoiceContactDetailsDto;
import rent.repo.api.user.UserDto;
import rent.repo.api.user.UserRepository;
import rent.repo.db.user.entity.ContactDetailsEntity;
import rent.repo.db.user.entity.InvoiceContactDetailsEntity;
import rent.repo.db.user.entity.UserEntity;
import rent.rest.api.RegistrationDto;

import java.util.List;

import static com.google.common.collect.Iterables.getFirst;

public class UserRepoImpl implements UserRepository {

    private final UserCrudRepo userCrudRepo;
    private final ContactDetailsCrudRepo contactDetailsCrudRepo;
    private final InvoiceContactDetailsCrudRepo invoiceContactDetailsCrudRepo;

    public UserRepoImpl(UserCrudRepo userCrudRepo,
                        ContactDetailsCrudRepo contactDetailsCrudRepo,
                        InvoiceContactDetailsCrudRepo invoiceContactDetailsCrudRepo
    ) {
        this.userCrudRepo = userCrudRepo;
        this.contactDetailsCrudRepo = contactDetailsCrudRepo;
        this.invoiceContactDetailsCrudRepo = invoiceContactDetailsCrudRepo;
    }

    @Override
    public UserDto authenticate(String email, String password) {
        List<UserDto> userDto = userCrudRepo.findByEmailAndPassword(email, password);
        return getFirst(userDto, null);
    }

    @Override
    public long addUser(RegistrationDto sessionUserDto) {
        UserEntity authDto = new UserEntity(sessionUserDto);
        final UserEntity savedUser = userCrudRepo.save(authDto);
        return savedUser.getId();
    }

    @Override
    public void activateUser(long userId) {
        final UserEntity user = userCrudRepo.findOne(userId);
        user.setActive(true);
        userCrudRepo.save(user);
    }

    @Override
    public UserDto getUser(long id) {
        final UserEntity userEnt = userCrudRepo.findOne(id);
        return new UserDtoImpl(userEnt);
    }

    @Override
    public UserDto getByEmail(String email) {
        return userCrudRepo.findByEmail(email);
    }

    @Override
    public ContactDetailsDto getContactDetails(long userId) {
        return contactDetailsCrudRepo.findByUserId(userId);
    }

    @Override
    public void addContactDetails(long userId, ContactDetailsDto contactDetailsDto) {
        ContactDetailsDto byUserId = contactDetailsCrudRepo.findByUserId(userId);
        if (byUserId != null) {
            contactDetailsCrudRepo.delete((ContactDetailsEntity) byUserId);
        }
        final ContactDetailsEntity contactDetailsEntity = new ContactDetailsEntity(userId, contactDetailsDto);
        contactDetailsCrudRepo.save(contactDetailsEntity);
    }

    @Override
    public void addInvoiceContactDetails(long userId, InvoiceContactDetailsDto invoiceContactDetailsDto) {
        InvoiceContactDetailsDto byUserId = invoiceContactDetailsCrudRepo.findByUserId(userId);
        if (byUserId != null) {
            invoiceContactDetailsCrudRepo.delete((InvoiceContactDetailsEntity) byUserId);
        }
        invoiceContactDetailsCrudRepo.save(new InvoiceContactDetailsEntity(userId, invoiceContactDetailsDto));
    }

    @Override
    public InvoiceContactDetailsDto getInvoiceContactDetails(long userId) {
        return invoiceContactDetailsCrudRepo.findByUserId(userId);
    }

    @Override
    public void inactivateUser(long userId) {
        final UserEntity userToBeInactivate = userCrudRepo.findOne(userId);
        userToBeInactivate.setActive(false);
        userCrudRepo.save(userToBeInactivate);
    }

    @Override
    public void changePassword(long userId, String newPassword) {
        final UserEntity userToBeInactivate = userCrudRepo.findOne(userId);
        userToBeInactivate.setPassword(newPassword);
        userCrudRepo.save(userToBeInactivate);
    }

    static class UserDtoImpl implements UserDto {

        private final long id;
        private final String username;
        private final String email;
        private final boolean active;
        private String password;

        UserDtoImpl(UserEntity ent) {
            id = ent.getId();
            username = ent.getFirstName();
            email = ent.getEmail();
            active = ent.isActive();
            password = ent.getPassword();
        }

        @Override
        public long getId() {
            return id;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getFirstName() {
            return username;
        }

        @Override
        public String getLastName() {
            return null;
        }

        @Override
        public String getEmail() {
            return email;
        }

        @Override
        public boolean isActive() {
            return active;
        }
    }
}
