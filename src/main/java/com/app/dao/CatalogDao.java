package com.app.dao;

import com.app.model.Catalog;
import com.app.model.CatalogItem;
import com.app.model.Category;
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

    public List<CatalogItem> getItems() {
        RowMapper<CatalogItem> rowMapper = (rs, rowNumber) -> mapCatalogItem(rs);
        return  jdbcTemplate.query("SELECT catalog.id AS cat_id, sub_types.type_id AS type_id, catalog.sub_type_id AS sub_type_id, " +
                "types.name AS type_name, sub_types.name AS sub_types_name, catalog.name AS item_name, description, price " +
                "FROM catalog " +
                "INNER JOIN sub_types ON catalog.sub_type_id  = sub_types.id " +
                "INNER JOIN types ON sub_types.type_id = types.id", rowMapper);
    }

    public List<Category> getCategories() {
        RowMapper<Category> rowMapper = (rs, rowNumber) -> mapCategoryItem(rs);
        return jdbcTemplate.query("SELECT * FROM types", rowMapper);
    }

    public List<Category> getSubCategories(int id) {
        RowMapper<Category> rowMapper = (rs, rowNumber) -> mapCategoryItem(rs);
        return jdbcTemplate.query("SELECT * FROM sub_types WHERE type_id = ?", rowMapper, id);
    }

    private CatalogItem mapCatalogItem(ResultSet rs) throws SQLException {
        CatalogItem item = new CatalogItem();

        item.setId(rs.getInt("cat_id"));
        item.setTypeId(rs.getInt("type_id"));
        item.setSubTypeId(rs.getInt("sub_type_id"));
        item.setTypeName(rs.getString("type_name"));
        item.setSubTypeName(rs.getString("sub_types_name"));
        item.setName(rs.getString("item_name"));
        item.setDescription(rs.getString("description"));
        item.setPrice(rs.getDouble("price"));

        return item;
    }


    private Category mapCategoryItem(ResultSet rs) throws SQLException {
        return new Category(rs.getInt("id"), rs.getString("name"));
    }



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
        catalog.setDescription(resultSet.getString("description"));
        catalog.setPrice(resultSet.getDouble("price"));
        catalog.setSubcategoryName(resultSet.getString("subcategoryName"));
        catalog.setCategoryName(resultSet.getString("categoryName"));

        return catalog;
    }
}
