package com.app.services;

import com.app.dao.CatalogDao;
import com.app.model.Catalog;
import com.app.model.CatalogItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    @Autowired
    private CatalogDao catalogDao;

    public List<CatalogItem> getCatalogItems() {
     return catalogDao.getItems();
    }





    public Double getProductPriceByName(String name) {
        return catalogDao.getProductByName(name).get(0).getPrice();
    }

    public List<Catalog> getCatalogList() {
        return catalogDao.getCatalog();
    }
}
