package com.pos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class LoginController {

    @RequestMapping("/")
    @ResponseBody
    public String testHomepage(){
        return "This is homepage";
    }

}
