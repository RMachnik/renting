package rent.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class BaseController {

    @RequestMapping("/login")
    String login(Model model) {
        return "login";
    }

    @RequestMapping("/")
    String index(Model model) {
        return "index";
    }
}
