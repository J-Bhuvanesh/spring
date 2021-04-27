package com.example.onetoone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OneToOneController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("api/insert")
    public String create(@RequestParam int hid, @RequestParam String hname, @RequestParam int wid, @RequestParam String wname) {

        String sql = "INSERT INTO HUSBAND VALUES (?,?)";
        String sql1 = "INSERT INTO WIFE VALUES (?,?,?)";
        int result = jdbcTemplate.update(sql, hid, hname);
        int result1 = jdbcTemplate.update(sql1, wid, wname, hid);
        if (result > 0 && result1 > 0)
            return "the gets updated";
        return "failed";

    }

    @GetMapping("api/display")

    public List<Map<String, Object>> display(@RequestParam int id){
        String sql="select w.name as wife,h.name as husband from husband  h left join wife w on h.id = w.husband_id where h.id=?";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql,id);
        return list;
    }
}