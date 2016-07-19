package rent.repo.api.user;

public interface InvoiceContactDetailsDto extends ContactDetailsDto {

    String getAccountNumber();

    String getBankName();

    String getPaymentMethod();

}
