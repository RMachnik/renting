package rent.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import rent.common.ValidationException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import static rent.common.util.Validators.notNullNotEmpty;

@Getter
@ToString
@EqualsAndHashCode
public class Email {

    private final String address;

    public Email(String address) {
        notNullNotEmpty(address, "email address");
        validateAddress(address);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    private void validateAddress(String address) {
        try {
            InternetAddress internetAddress = new InternetAddress(address);
            internetAddress.validate();
        } catch (AddressException e) {
            throw new ValidationException("Email address has invalid format", e);
        }
    }
}
