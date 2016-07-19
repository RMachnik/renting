package rent.domain.user;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static rent.domain.util.TestUtil.shouldThrowException;

public class PhoneTest {

    @Test
    public void shouldValidatePhone() {
        shouldThrowException(() -> new Phone(""), "phone number");
        shouldThrowException(() -> new Phone("123"), "Phone number has invalid format.");
    }

    @Test
    public void shouldParsePhone() {
        final String number = "123 456 7890";

        Phone phone = new Phone(number);

        assertThat(phone.getNumber()).isEqualTo(number);
    }
}
