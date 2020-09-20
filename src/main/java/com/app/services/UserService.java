package com.app.services;

import com.app.dao.UserDao;
import com.app.model.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public String getFirstUserFirstName() {
        return userDao.getUsers().get(1).getFirstName();
    }

    public void storeUser(Registration reg) {
        userDao.storeUser(reg);
    }
}
