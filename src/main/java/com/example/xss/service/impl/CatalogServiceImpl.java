package com.example.xss.service.impl;

import com.example.xss.entity.Catalog;
import com.example.xss.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("catalogService")
public class CatalogServiceImpl implements CatalogService {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(Catalog catalog) {
        String sql = "insert into xscatalog(xsName, xsUrl) values(?, ?);";
        jdbcTemplate.update(sql, catalog.getXsName(), catalog.getXsUrl());
    }

    @Override
    public void batchUpdate(List<Catalog> catalogList) {
        String sql = "insert into xscatalog(xsName, xsUrl) values(?, ?);";
    }
}
