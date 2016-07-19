package rent.domain.user;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static rent.domain.util.TestUtil.shouldThrowException;

public class EmailTest {

    @Test
    public void shouldValidateEmail() {
        shouldThrowException(() -> new Email("invalid"), "Email address has invalid format");
        shouldThrowException(() -> new Email(""), "email address");
    }

    @Test
    public void shouldRecogniseProperEmail() {
        final String address = "test@email";

        Email email = new Email(address);

        assertThat(email.getAddress()).isEqualTo(address);
    }
}
