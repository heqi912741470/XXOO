package com.example.xss;

import com.example.xss.entity.Catalog;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
class XssApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Test
    public int add(Catalog catalog) {
        return jdbcTemplate.update("insert into xscatalog(xsName, xsUrl) values(?, ?)");
    }

    @Test
    public void findxsCatalogList() {
        List<Catalog> list = jdbcTemplate.query("select * from xscatalog", new BeanPropertyRowMapper(Catalog.class));
        System.out.println(list);
    }
    @Test
    public void queryCatalog(){
        List<Map<String, Object>>list = jdbcTemplate.queryForList("select * from xscatalog");
        System.out.println(list);
    }
}
