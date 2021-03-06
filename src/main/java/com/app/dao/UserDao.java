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
    private JdbcTemplate jdbcTemplate2;

    public void storeUser(Registration reg) throws NullPointerException {
        jdbcTemplate2.update("INSERT INTO users (username, password, first_name, last_name, email, phone, birth_date, city_id, card_nr) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                reg.getUsername(), reg.getPassword(), reg.getFirstName(), reg.getLastName(), reg.getEmail(), reg.getPhone(), reg.getBirthDate(), reg.getCityId(), reg.getCardNr());
    }

    public List<User> getUsers() {
        RowMapper<User> rowMapper = (resultSet, rowNumber) -> mapUser(resultSet); //Lambda
        return jdbcTemplate2.query("SELECT * FROM users", rowMapper);
    }

    public List<User> getUserByUsername(String username) {
        RowMapper<User> rowMapper = (resultSet, rowNumber) -> mapUser(resultSet);
        return jdbcTemplate2.query("SELECT * FROM users WHERE username = ?", rowMapper, username);
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setPhone(resultSet.getString("phone"));
        user.setPassword(resultSet.getString("password"));
        user.setBirthDate(resultSet.getDate("birth_date"));
        user.setCityId(resultSet.getInt("city_id"));
        user.setCardNr(resultSet.getString("card_nr"));

        return user;
    }
}
