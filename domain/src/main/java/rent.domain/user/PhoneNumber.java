package rent.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import rent.common.ValidationException;

import static rent.common.util.Validators.nullOrEmpty;

/**
 * Accepted formats.
 * 1234567890
 * 123-456-7890
 * 123-456-7890
 * 123-456-7890
 * (123)-456-7890
 * 123.456.7890
 * 123 456 7890
 */
@Getter
@ToString
@EqualsAndHashCode
public class PhoneNumber {

    private final String number;

    public PhoneNumber(String number) {
        nullOrEmpty(number, "phone number");
        validatePhoneNumber(number);
        this.number = number;
    }

    private void validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) {
            return;
        }
        //validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
            return;
        }
        //validating phone number with extension length from 3 to 5
        else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) {
            return;
        }
        //validating phone number where area code is in braces ()
        else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) {
            return;
        }
        throw new ValidationException("Phone number has invalid format.");
    }
}
