package com.example.xss.job;


import com.example.xss.entity.Catalog;
import com.example.xss.service.CatalogService;
import com.example.xss.xs.XsCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@EnableScheduling
@Component
public class XsCrawlerJob {
    @Autowired
    private CatalogService catalogService;

    @Scheduled(cron = "40 44 0 * * ?")
    public void job() {
        List<Catalog> catalogList = new XsCrawler().doGet();
        if (catalogList.size() > 0) {
            catalogList.forEach(e -> {
                catalogService.add(e);
            });
        }
        System.out.println(catalogList.size());
    }
}
