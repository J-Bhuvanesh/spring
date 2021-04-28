package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Controller {
	

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@GetMapping("hello")
	public String create(@RequestParam int id,@RequestParam String name,@RequestParam int age) {
		
		String sql="INSERT INTO STUDENT VALUES (?,?,?)";
		int result=jdbcTemplate.update(sql,id,name,age);
		if(result>0)
			System.out.println("data inserted successfully.....");
		return "successfully inserted";}
	 @GetMapping("displayall")
	    public ResponseEntity<List<Student>> display() {

	        List<Student> students=jdbcTemplate.query("SELECT * FROM STUDENT",new BeanPropertyRowMapper<>(Student.class));
	        return new ResponseEntity<>(students,HttpStatus.OK);


	    }
	 @GetMapping("update")
	    public String update(@RequestParam int id,@RequestParam String name,@RequestParam int age ,@RequestParam int id1){
	        String sql="update student set id=?,name=?,age=? where id=?;";
	        int result=jdbcTemplate.update(sql,id,name,age,id1);
	        if(result>0)
	            return  "update successful and the id is "+id+" ,name is "+name+" and age is "+age+".";
	        return "ok";
	    }

	    @GetMapping("delete")
	    public String delete(@RequestParam int id){
	        String sql="DELETE FROM STUDENT WHERE ID=?";
	        int result=jdbcTemplate.update(sql,id);
	        if(result>0 )
	            return "successfully deleted";
	        return "failed";
	    }

}
