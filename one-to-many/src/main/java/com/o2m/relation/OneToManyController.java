package com.o2m.relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OneToManyController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("api/customer/order")
    public String create(@RequestParam int customerid, @RequestParam String name, @RequestParam String order, @RequestParam String supplier) {

        String sql = "insert into customers values (?,?)";
        String sql1 = "insert into orders values (?,?,?)";
        int result = jdbcTemplate.update(sql, customerid, name);
        int result1 = jdbcTemplate.update(sql1,order,supplier, customerid);
        if (result > 0 && result1 > 0)
            return "the gets updated in one to many";
        return "failed";

    }

    @GetMapping("api/display/customers/name")

    public List<Map<String, Object>> display(@RequestParam int id) {
        String sql = "select c.customerid,c.name,o.ordertype,o.suppliername from customers c left join orders o on c.customerid=o.customerid where c.customerid=?";

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,id);


        return list;
    }

}
