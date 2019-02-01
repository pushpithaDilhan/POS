package com.pos.controllers;

import com.pos.services.UserLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin
@Controller
@RequestMapping(value = "/")
public class LoginController {

    @RequestMapping(value = "/" , method = POST)
    @ResponseBody
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password) throws SQLException, NoSuchAlgorithmException {
        return UserLoginService.getUserLoginService().userAuthentication(username, password);
    }

    @RequestMapping(value = "/validate" , method = GET)
    @ResponseBody
    public String validateUser(@CookieValue("pos") String cookie) throws SQLException {
        return UserLoginService.getUserLoginService().userAuthorization(cookie);
    }

    // log out a user - flush cookies
    @RequestMapping(value = "/logout" , method = GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String logoutUser() {

        Boolean user_validity = true;
        if(user_validity) {
            return "u01";
        }else{
            return "invalid";
        }
    }

}
