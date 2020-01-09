//package com.example.xss.Controller;
//
//import com.example.xss.entity.Catalog;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.Map;
//
//
//@EnableScheduling
//@Component
//public class jdbcController {
//    @Resource
//    JdbcTemplate jdbcTemplate;
//    @Scheduled(cron = "0/1 * * * * ?")
//    public List<Map<String,Object>> queryCatalog(){
//        List<Map<String, Object>>list = jdbcTemplate.queryForList("select * from xscatalog");
//        return list;
//    }
//    Catalog catalog =new Catalog();
//    @Scheduled(cron = "0/1 * * * * ?")
//    public int add() {
//        String xsName =catalog.getXsName();
//        String xsUrl = catalog.getXsUrl();
//        String sql = "insert into xscatalog(xsName, xsUrl) values(?, ?)";
//        return jdbcTemplate.update(sql,xsName,xsUrl);
//    }
//
//}
