package rent.domain.user;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static rent.domain.util.TestUtil.shouldThrowException;

public class PhoneNumberTest {

    @Test
    public void shouldValidatePhone() {
        shouldThrowException(() -> new PhoneNumber(""), "phone number");
        shouldThrowException(() -> new PhoneNumber("123"), "Phone number has invalid format.");
    }

    @Test
    public void shouldParsePhone() {
        final String number = "123 456 7890";

        PhoneNumber phoneNumber = new PhoneNumber(number);

        assertThat(phoneNumber.getNumber()).isEqualTo(number);
    }
}
