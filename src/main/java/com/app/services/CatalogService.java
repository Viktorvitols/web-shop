package com.app.services;

import com.app.dao.CatalogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {

    @Autowired
    private CatalogDao catalogDao;

    public Double getProductPriceByName(String name) {
        return catalogDao.getProductByName(name).get(0).getPrice();
    }
}
