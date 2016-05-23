package rent.common;

import org.junit.Test;
import rent.common.util.SerializationUtil;

import java.util.Map;
import java.util.Optional;

import static java.util.Collections.singletonMap;
import static java.util.Optional.*;
import static org.fest.assertions.Assertions.assertThat;

public class SerializationUtilTest {

    @Test
    public void shouldEscapeWhitespace() {
        Map<String, String> jsonObject = singletonMap("key", "lorem\tipsum");
        String actual = SerializationUtil.toJson(jsonObject);
        assertThat(actual).isEqualTo("{\"key\":\"lorem\\\\tipsum\"}");
    }

    @Test
    public void shouldEscapeSpecialCharacters() {
        Map<String, String> jsonObject = singletonMap("horseName", "Jonny's Emerald");
        String actual = SerializationUtil.toJson(jsonObject);
        assertThat(actual).isEqualTo("{\"horseName\":\"Jonny\\u0027s Emerald\"}");
    }

    @Test
    public void shouldInlcudeNullValues() {
        Map<String, String> jsonObject = singletonMap("key", null);
        String actual = SerializationUtil.toJson(jsonObject);
        assertThat(actual).isEqualTo("{\"key\":null}");
    }

    @Test
    public void shouldSerializeEmptyOptionalAsNull() {
        Map<String, Optional<?>> jsonObject = singletonMap("key", empty());
        String actual = SerializationUtil.toJson(jsonObject);
        assertThat(actual).isEqualTo("{\"key\":null}");
    }

    @Test
    public void shouldSerializeNonEmptyOptional() {
        Map<String, Optional<?>> jsonObject = singletonMap("key", of(123));
        String actual = SerializationUtil.toJson(jsonObject);
        assertThat(actual).isEqualTo("{\"key\":123}");
    }
}