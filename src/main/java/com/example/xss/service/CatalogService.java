package com.example.xss.service;

import com.example.xss.entity.Catalog;

import java.util.List;

public interface CatalogService {
    void add(Catalog catalog);

    void batchUpdate(List<Catalog> catalogList);
}
