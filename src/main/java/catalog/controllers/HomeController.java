package catalog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: nickolay
 * Date: 8/10/13
 * Time: 12:15 PM
 */

@Controller
@RequestMapping({"/","/catalog", "/home"})
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String startPage(){
        return "home";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login-failure")
    public String loginFailure(){
        return "login?login-error=t";
    }

}
