package com.pos.controllers;

import com.pos.services.UserLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/")
public class LoginController {

    @RequestMapping(value = "/", method = GET)
    @ResponseBody
    public String testHome(){
        return "Server is up and running.";
    }

    @RequestMapping(value = "/" , method = POST)
    @ResponseBody
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        UserLoginService userLoginService = UserLoginService.getUserLoginService();
        String status = userLoginService.userAuthentication(username, password);
        return status;
    }

}
