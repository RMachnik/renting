package rent.rest.controller.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rent.domain.user.Activation;
import rent.repo.api.Repositories;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static rent.common.util.RestUtil.perform;

@Log4j2
@RestController
@RequestMapping("/activate")
public class ActivationController {

    @Autowired
    Repositories repositories;

    @RequestMapping(value = "/{token}", method = GET)
    public ResponseEntity activate(@PathVariable("token") String token) {
        return perform(() -> {
            Activation activation = new Activation(token, repositories);
            activation.activateAccount();
            return "";
        }, "There was problem with activation.", log);
    }
}
