package com.app.services;

import com.app.dao.CatalogDao;
import com.app.model.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {

    @Autowired
    private CatalogDao catalogDao;

    public Double getProductPriceByName(String name) {
        return catalogDao.getProductByName(name).get(0).getPrice();
    }

    public Catalog getItem() {
        Catalog catalogItem = new Catalog();
        for (Catalog item : catalogDao.getItemFromCatalog()) {
           catalogItem = item;
        }
        return catalogItem;
    }
}
