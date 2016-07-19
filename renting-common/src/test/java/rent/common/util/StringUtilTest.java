package rent.common.util;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static rent.common.util.StringUtil.j;

public class StringUtilTest {

    @Test
    public void shouldConvertApostropheToQuotation() {
        assertThat(j("this is 'some' text")).isEqualTo("this is \"some\" text");
        assertThat(j("'")).isEqualTo("\"");
        assertThat(j("'a")).isEqualTo("\"a");
        assertThat(j("a'")).isEqualTo("a\"");
    }

    @Test
    public void shouldReturnTheSameTextIfNoApostrophesPresent() {
        assertThat(j("this is \"some\" text")).isEqualTo("this is \"some\" text");
    }

    @Test
    public void shouldReturnNullIfTheSourceTextIsNull() {
        assertThat(j(null)).isNull();
    }
}