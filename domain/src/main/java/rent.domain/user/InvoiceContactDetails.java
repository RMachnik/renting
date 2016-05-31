package rent.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import rent.repo.api.user.InvoiceContactDetailsDto;

import static rent.domain.user.PaymmentMethod.valueOf;

@Getter
@EqualsAndHashCode
public class InvoiceContactDetails extends ContactDetails {

    private final String accountNumber;
    private final String bankName;
    private final PaymmentMethod paymentMethod;

    public InvoiceContactDetails(InvoiceContactDetailsDto invoiceContactDetailsDto) {
        super(invoiceContactDetailsDto);
        this.accountNumber = invoiceContactDetailsDto.getAccountNumber();
        this.bankName = invoiceContactDetailsDto.getBankName();
        this.paymentMethod = valueOf(invoiceContactDetailsDto.getPaymentMethod());
    }
}
