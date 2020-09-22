package com.app.dao;

import com.app.model.Registration;
import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void storeUser(Registration reg) throws NullPointerException {
        jdbcTemplate.update("INSERT INTO users (username, password, first_name, last_name, email, phone, birth_date, city_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                reg.getUsername(), reg.getPassword(), reg.getFirstName(), reg.getLastName(), reg.getEmail(), reg.getPhone(), reg.getBirthDate(), reg.getCityId());
    }

    public List<User> getUsers() {
        RowMapper<User> rowMapper = (resultSet, rowNumber) -> mapUser(resultSet); //Lambda
        return jdbcTemplate.query("SELECT * FROM users", rowMapper);
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt("id"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setPhone(resultSet.getString("phone"));
        user.setPassword(resultSet.getString("password"));

        return user;
    }
}
