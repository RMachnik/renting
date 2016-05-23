package rent.domain.user;

import rent.common.ValidationException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import static rent.common.util.Validators.notNullNotEmpty;

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

    @Override
    public int hashCode() {
        return address != null ? address.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Email email = (Email) o;

        return address != null ? address.equals(email.address) : email.address == null;

    }

    @Override
    public String toString() {
        return address;
    }
}
