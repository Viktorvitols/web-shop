package com.app.dao;

import com.app.model.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CatalogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Catalog> getProductByName(String name) {
        RowMapper<Catalog> rowMapper = (resultSet, rowNumber) -> mapCatalog(resultSet);
        return jdbcTemplate.query("SELECT * FROM catalog WHERE name = ?", rowMapper, name);
    }

    public List<Catalog> getCatalog() {
        RowMapper<Catalog> rowMapper = (resultSet, rowNumber) -> mapCatalog(resultSet);
        return jdbcTemplate.query
                ("SELECT catalog.id, catalog.name, catalog.description, catalog.price, sub_types.name AS subcategoryName, types.name AS categoryName\n" +
                "FROM ((catalog\n" +
                "INNER JOIN sub_types ON catalog.sub_type_id = sub_types.id)\n" +
                "INNER JOIN types ON sub_types.type_id = types.id)", rowMapper);
    }

    public Catalog mapCatalog(ResultSet resultSet) throws SQLException {
        Catalog catalog = new Catalog();

        catalog.setId(resultSet.getInt("id"));
        catalog.setName(resultSet.getString("name"));
//        catalog.setSubTypeId(resultSet.getInt("sub_type_id"));
//        catalog.setManufacturerId(resultSet.getInt("manufacturer_id"));
        catalog.setDescription(resultSet.getString("description"));
        catalog.setPrice(resultSet.getDouble("price"));
        catalog.setSubcategoryName(resultSet.getString("subcategoryName"));
        catalog.setCategoryName(resultSet.getString("categoryName"));

        return catalog;
    }
}
