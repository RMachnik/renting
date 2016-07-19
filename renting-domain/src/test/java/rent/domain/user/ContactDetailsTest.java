package rent.domain.user;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static rent.repo.stationary.user.StaticContactDetailsDto.CONTACT_DETAILS_DTO;

public class ContactDetailsTest {

    @Test
    public void shouldFillInAllProps() {
        ContactDetails contactDetails = new ContactDetails(CONTACT_DETAILS_DTO);

        assertThat(contactDetails.getFullName()).isEqualTo(CONTACT_DETAILS_DTO.getFullName());
        assertThat(contactDetails.getIdentification()).isEqualTo(CONTACT_DETAILS_DTO.getIdentification());
        assertThat(contactDetails.getAddress()).isNotNull();
        assertThat(contactDetails.getPhone().getNumber()).isEqualTo(CONTACT_DETAILS_DTO.getPhoneNumber());
    }
}