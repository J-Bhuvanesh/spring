package com.m2m.relation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ManyController {
	   @Autowired
	    private JdbcTemplate jdbcTemplate;

	    @GetMapping("api/studentid/customerid")
	    public String create(@RequestParam int studentid, @RequestParam int customerid) {

	        String sql = "insert into studencourse values(?,?)";

	        int result = jdbcTemplate.update(sql, studentid,customerid);

	        if (result > 0)
	            return "the gets updated in studencourse ";
	        return "failed";
	    }

	    @GetMapping("api/display/student/course")

	    public Object display(@RequestParam int studentid) {
	        String sql = "select s.rollno,s.name,s.age,c.courseid,c.cname,c.credit from student s left join studencourse sc on sc.rollno=s.rollno left join course c on c.courseid =sc.courseid where s.rollno=?";

	        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,studentid);
	        if(list.isEmpty())
	            return "no data is present";
	        return list;
	    }

}
