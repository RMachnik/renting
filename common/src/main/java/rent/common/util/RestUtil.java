package rent.common.util;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.Callable;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;
import static rent.common.util.SerializationUtil.toJson;

public class RestUtil {

    public static ResponseEntity perform(Callable action, Object errorMsg, Logger logger) {
        try {
            final Object response = action.call();
            return ok(toJson(response));
        } catch (Exception ex) {
            logger.error(errorMsg.toString(), ex);
            return badRequest().body(toJson(errorMsg));
        }
    }

}
