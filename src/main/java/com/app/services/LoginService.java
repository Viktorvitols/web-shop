package com.app.services;

import com.app.dao.UserDao;
import com.app.model.Login;
import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    private UserDao userDao;

    public Integer getUserId(Login login) throws SQLException {
        List<User> users = userDao.getUserByUsername(login.getUsername());

        if (users.size() > 1) {
            System.out.println("There is more then one user with username " + login.getUsername());
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
//        login.setPassword(encoder.matches(login.getPassword()));

        if (!users.isEmpty() && encoder.matches(login.getPassword(),
                users.get(0).getPassword())) {
            return users.get(0).getId();
        }
            return null;
    }

    public String getUsername(Login login) throws SQLException {
        List<User> users = userDao.getUserByUsername(login.getUsername());
        return users.get(0).getUsername();

    }
}
