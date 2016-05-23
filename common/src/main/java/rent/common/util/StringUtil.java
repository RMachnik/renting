package rent.common.util;

public class StringUtil {

    public static String j(String jsonWithApostrophes) {

        if (jsonWithApostrophes == null) {
            return null;
        }

        return jsonWithApostrophes.replace('\'', '\"');

    }
}
