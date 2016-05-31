package rent.domain.user;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static rent.repo.stationary.user.StaticContactDetailsDto.INVOICE_CONTACT_DETAILS_DTO;

public class InvoiceContactDetailsTest {

    @Test
    public void shouldBasicConstructor() {
        InvoiceContactDetails invoiceContactDetails = new InvoiceContactDetails(INVOICE_CONTACT_DETAILS_DTO);

        assertThat(invoiceContactDetails.getBankName()).isEqualTo(INVOICE_CONTACT_DETAILS_DTO.getBankName());
        assertThat(invoiceContactDetails.getAccountNumber()).isEqualTo(INVOICE_CONTACT_DETAILS_DTO.getAccountNumber());
        assertThat(invoiceContactDetails.getPaymentMethod().name()).isEqualTo(INVOICE_CONTACT_DETAILS_DTO.getPaymentMethod());
    }
}