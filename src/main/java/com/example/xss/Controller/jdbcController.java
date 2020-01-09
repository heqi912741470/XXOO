package com.example.xss.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController

public class jdbcController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @GetMapping("/jdbc")
    public List<Map<String,Object>> queryCatalog(){
        List<Map<String, Object>>list = jdbcTemplate.queryForList("select * from xscatalog");
        return list;
    }
}
