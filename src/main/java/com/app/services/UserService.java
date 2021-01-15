package com.app.services;

import com.app.dao.UserDao;
import com.app.model.Registration;
import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public String getFirstUserFirstName() {
        return userDao.getUsers().get(1).getFirstName();
    }

    public void storeUser(Registration reg) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        reg.setPassword(encoder.encode(reg.getPassword()));
        userDao.storeUser(reg);
    }

    public List<User> getAllUsers() {
        return userDao.getUsers();
    }

}
