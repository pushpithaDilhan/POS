package com.pos.services;

import com.pos.models.User;
import com.pos.util.DataRepository;
import java.util.ArrayList;

public class UserLoginService {
    private static UserLoginService userLoginService;

    static{
        if (userLoginService == null){
            userLoginService = new UserLoginService();
        };
    }

    public static UserLoginService getUserLoginService(){
        return userLoginService;
    }

    public String userAuthentication(String username, String password){
        ArrayList<User> users = DataRepository.getUserList();
        System.out.println(users);
        for (User user: users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user.getUser_id();
            }
        }
        return "unauthorized";
    }
}
