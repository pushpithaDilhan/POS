package com.pos.controllers;

import com.pos.models.User;
import com.pos.services.UserLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/")
public class LoginController {

    @RequestMapping("/")
    @ResponseBody
    public String testHomepage() throws IOException {
        String username = "0AD28F73D534E4CF3941A477375D1D75" ;
        String password = "E3A6B8B322FD02D0A5172256ECED4301";
        UserLoginService userLoginService = UserLoginService.getUserLoginService();
        User user = userLoginService.GetUser(username, password);
        return "User logged in " + user.toString();
    }

    @RequestMapping(value = "/" , method = POST)
    @ResponseBody
    public String loginUser(@RequestBody String username, @RequestBody String password) throws IOException {
        UserLoginService userLoginService = UserLoginService.getUserLoginService();
        User user = userLoginService.GetUser(username, password);
        return user.toString();
    }

}
