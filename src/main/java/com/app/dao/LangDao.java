package com.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.HashMap;

@Repository
public class LangDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public HashMap<String, String> getTranslations(Integer langId, String page) {
        return jdbcTemplate.query("SELECT label, translation FROM translations WHERE lang_id = ? " +
                        "AND (page = ? OR page = 'all')",
                (ResultSet rs) -> {
                    HashMap<String, String> result = new HashMap<>();

                    while (rs.next()) {
                        result.put(rs.getString("label"), rs.getString("translation"));
                    }

                    return result;
                },
                langId, page);
    }
}
