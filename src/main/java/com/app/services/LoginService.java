package com.app.services;

import com.app.dao.UserDao;
import com.app.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class LoginService {

    @Autowired
    UserDao userDao;

    public int getUserId(Login login) throws SQLException {
        String username = login.getUsername();
        return userDao.getUserByUsername(username).get(0).getId();
    }
}
