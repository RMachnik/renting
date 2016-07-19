package rent.common.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.thoughtworks.xstream.XStream;
import org.unbescape.json.JsonEscape;

import java.lang.reflect.Type;
import java.util.Optional;

public class SerializationUtil {

    private static final Gson GSON = createJsonSerializer();
    private static final XStream XSTREAM = new XStream();

    static {
        XSTREAM.autodetectAnnotations(true);
    }

    public static String toJson(Object data) {
        return GSON.toJson(data);
    }

    public static String toXml(Object data) {
        return XSTREAM.toXML(data);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    private static Gson createJsonSerializer() {
        return new GsonBuilder()
                .registerTypeAdapter(Optional.class, new OptionalSerializer())
                .registerTypeAdapter(String.class, new StringSerializer())
                .serializeNulls()
                .create();
    }

    private static class StringSerializer implements JsonSerializer<String> {

        @Override
        public JsonElement serialize(String obj, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(JsonEscape.escapeJson(obj));
        }
    }

    private static class OptionalSerializer implements JsonSerializer<Optional> {

        @Override
        public JsonElement serialize(Optional obj, Type type, JsonSerializationContext context) {
            return obj.isPresent() ? context.serialize(obj.get()) : null;
        }
    }
}
