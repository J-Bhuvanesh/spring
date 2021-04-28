package com.constrain.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ConstrainController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("api/person/detail")
    public String add(@RequestParam int pid, @RequestParam String plastname,@RequestParam String pfname,@RequestParam int page) {

        String sql = "insert into Persons values (?,?,?,?);";
        int result=0;
       try { 
    	   result = jdbcTemplate.update(sql,pid,plastname,pfname,page);
    		   }
       catch(Exception e) {}

        if (result > 0)
            return "the gets updated in constrain persons table ";
        
        return "enter the details correctly age must greater than 18 and last name should not be null";
    }
}
