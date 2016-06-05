package rent.repo.db.user.entity;

import rent.repo.api.user.AddressDto;
import rent.repo.api.user.InvoiceContactDetailsDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InvoiceContactDetailsEntity implements InvoiceContactDetailsDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;
    private String fullName;
    private String identification;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String postalCode;
    private String city;
    private String accountNumber;
    private String bankName;
    private String paymentMethod;

    public InvoiceContactDetailsEntity() {
    }

    public InvoiceContactDetailsEntity(long userId, InvoiceContactDetailsDto dataDto) {
        this.userId = userId;
        this.fullName = dataDto.getFullName();
        this.identification = dataDto.getIdentification();
        this.phoneNumber = dataDto.getPhoneNumber();
        this.addressLine1 = dataDto.getAddress().getAddressLine1();
        this.addressLine2 = dataDto.getAddress().getAddressLine2();
        this.postalCode = dataDto.getAddress().getPostalCode();
        this.city = dataDto.getAddress().getCity();
        this.bankName = dataDto.getBankName();
        this.accountNumber = dataDto.getAccountNumber();
        this.paymentMethod = dataDto.getPaymentMethod();
    }

    public long getId() {
        return id;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String getBankName() {
        return bankName;
    }

    @Override
    public String getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public AddressDto getAddress() {
        return new AddressDtoImpl(addressLine1, addressLine2, postalCode, city);
    }

    @Override
    public String getIdentification() {
        return identification;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

}
