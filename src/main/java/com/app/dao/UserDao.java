package com.app.dao;

import com.app.model.Login;
import com.app.model.Registration;
import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void storeUser(Registration reg) throws NullPointerException {
        jdbcTemplate.update("INSERT INTO users (username, password, first_name, last_name, email, phone, birth_date, city_id, card_nr) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                reg.getUsername(), reg.getPassword(), reg.getFirstName(), reg.getLastName(), reg.getEmail(), reg.getPhone(), reg.getBirthDate(), reg.getCityId(), reg.getCardNr());
    }

    public List<User> login(Login login) throws SQLException {
        RowMapper<User> rowMapper = (resultSet, rowNumber) -> mapUser(resultSet);
        return jdbcTemplate.query("SELECT * FROM users WHERE username = '" + login.getUsername() + "' AND password = '" + login.getPassword() + "'", rowMapper);
    }



    public List<User> getUsers() {
        RowMapper<User> rowMapper = (resultSet, rowNumber) -> mapUser(resultSet); //Lambda
        return jdbcTemplate.query("SELECT * FROM users", rowMapper);
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt("id"));
        user.getUsername(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setPhone(resultSet.getString("phone"));
        user.setPassword(resultSet.getString("password"));
        user.getBirthDate(resultSet.getString("birth_date"));
        user.getCityId(resultSet.getString("city_id"));
        user.getCardNr(resultSet.getString("card_nr"));

        return user;
    }
}
