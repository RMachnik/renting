package rent.domain.util;

import static junit.framework.TestCase.fail;
import static org.fest.assertions.Assertions.assertThat;

public class TestUtil {

    public static void shouldThrowException(Runnable action, String msg) {
        try {
            action.run();
            fail("it should throw exception");
        } catch (RuntimeException ex) {
            assertThat(ex.getMessage()).contains(msg);
        }
    }

}
