package com.pos.services;

import com.pos.util.DatabaseAccess;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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

    public String userAuthentication(String username, String password) throws SQLException, NoSuchAlgorithmException {
        return DatabaseAccess.getDatabaseAccess().Login(username, password);
    }

    public String userAuthorization(String cookie) throws SQLException {
        return DatabaseAccess.getDatabaseAccess().Authorize(cookie);
    }
}
