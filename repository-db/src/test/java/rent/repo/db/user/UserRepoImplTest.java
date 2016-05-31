package rent.repo.db.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rent.repo.api.user.ContactDetailsDto;
import rent.repo.api.user.InvoiceContactDetailsDto;
import rent.repo.api.user.UserDto;
import rent.repo.api.user.UserRepository;
import rent.repo.db.RepoDbConfig;
import rent.repo.db.TestConfig;
import rent.repo.db.user.entity.ContactDetailsEntity;
import rent.repo.db.user.entity.InvoiceContactDetailsEntity;

import static org.fest.assertions.Assertions.assertThat;
import static rent.repo.stationary.user.StaticContactDetailsDto.CONTACT_DETAILS_DTO;
import static rent.repo.stationary.user.StaticContactDetailsDto.INVOICE_CONTACT_DETAILS_DTO;
import static rent.repo.stationary.user.StaticRegistrationDto.REGISTRATION_DTO;
import static rent.repo.stationary.user.StaticUserDto.USER_DTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
        RepoDbConfig.class,
        TestConfig.class,
})
@ActiveProfiles("test")
public class UserRepoImplTest {

    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.addUser(REGISTRATION_DTO);
    }

    @Test
    public void shouldAddAndGetUser() {
        UserDto userAuthentication = userRepository.authenticate(USER_DTO.getUserName(), USER_DTO.getPassword());

        assertThat(userAuthentication.getUserName()).isEqualTo(USER_DTO.getUserName());
        assertThat(userAuthentication.getId()).isEqualTo(USER_DTO.getId());
        assertThat(userAuthentication.getPassword()).isEqualTo(USER_DTO.getPassword());
    }

    @Test
    public void shouldActivateUser() {
        userRepository.activateUser(USER_DTO.getId());

        final UserDto user = userRepository.getUser(USER_DTO.getId());

        assertThat(user.isActive()).isTrue();
    }

    @Test
    public void shouldAddContactDetails() {
        UserDto user = userRepository.getUser(USER_DTO.getId());
        userRepository.addContactDetails(user.getId(), CONTACT_DETAILS_DTO);

        ContactDetailsDto contactDetails = userRepository.getContactDetails(user.getId());

        assertContactDetails(contactDetails);
    }

    @Test
    public void shouldAddInvoiceContactDetails() {
        UserDto user = userRepository.getUser(USER_DTO.getId());
        userRepository.addInvoiceContactDetails(user.getId(), INVOICE_CONTACT_DETAILS_DTO);

        InvoiceContactDetailsDto contactDetails = userRepository.getInvoiceContactDetails(user.getId());

        assertContactDetails(contactDetails);
        assertThat(contactDetails.getBankName()).isEqualTo(INVOICE_CONTACT_DETAILS_DTO.getBankName());
        assertThat(contactDetails.getAccountNumber()).isEqualTo(INVOICE_CONTACT_DETAILS_DTO.getAccountNumber());
        assertThat(contactDetails.getPaymentMethod()).isEqualTo(INVOICE_CONTACT_DETAILS_DTO.getPaymentMethod());

    }

    @Test
    public void shouldUpdateInvoiceContactDetails() {
        UserDto user = userRepository.getUser(USER_DTO.getId());
        userRepository.addInvoiceContactDetails(user.getId(), INVOICE_CONTACT_DETAILS_DTO);
        InvoiceContactDetailsEntity contactDetails1 = (InvoiceContactDetailsEntity) userRepository.getInvoiceContactDetails(user.getId());

        userRepository.addInvoiceContactDetails(user.getId(), INVOICE_CONTACT_DETAILS_DTO);
        InvoiceContactDetailsEntity contactDetails2 = (InvoiceContactDetailsEntity) userRepository.getInvoiceContactDetails(user.getId());

        assertThat(contactDetails1.getId()).isNotEqualTo(contactDetails2.getId());
    }

    @Test
    public void shouldUpdateContactDetails() {
        UserDto user = userRepository.getUser(USER_DTO.getId());
        userRepository.addContactDetails(user.getId(), CONTACT_DETAILS_DTO);
        ContactDetailsEntity contactDetails1 = (ContactDetailsEntity) userRepository.getContactDetails(user.getId());

        userRepository.addContactDetails(user.getId(), CONTACT_DETAILS_DTO);
        ContactDetailsEntity contactDetails2 = (ContactDetailsEntity) userRepository.getContactDetails(user.getId());

        assertThat(contactDetails1.getId()).isNotEqualTo(contactDetails2.getId());
    }

    private void assertContactDetails(ContactDetailsDto contactDetails) {
        assertThat(contactDetails).isNotNull();
        assertThat(contactDetails.getPhoneNumber()).isEqualTo(CONTACT_DETAILS_DTO.getPhoneNumber());
        assertThat(contactDetails.getIdentification()).isEqualTo(CONTACT_DETAILS_DTO.getIdentification());
        assertThat(contactDetails.getFullName()).isEqualTo(CONTACT_DETAILS_DTO.getFullName());
        assertThat(contactDetails.getAddress().getAddressLine1()).isEqualTo(CONTACT_DETAILS_DTO.getAddress().getAddressLine1());
        assertThat(contactDetails.getAddress().getAddressLine2()).isEqualTo(CONTACT_DETAILS_DTO.getAddress().getAddressLine2());
        assertThat(contactDetails.getAddress().getCity()).isEqualTo(CONTACT_DETAILS_DTO.getAddress().getCity());
        assertThat(contactDetails.getAddress().getPostalCode()).isEqualTo(CONTACT_DETAILS_DTO.getAddress().getPostalCode());
    }
}
