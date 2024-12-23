package com.karthik.MavenSpringBoot.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.karthik.MavenSpringBoot.model.Employee;
@Repository
public class EmployeeRepo{
	
	JdbcTemplate jdbc;
	
	

	public JdbcTemplate getJdbc() {
		return jdbc;
	}
@Autowired
	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	
	public void save(Employee e) {
		String query= "insert into employee(eid,ename) values(?,?);";
		
		
		int rows = jdbc.update(query,e.getEid(),e.getEname());
		System.out.println(rows);
		
	}

	public List<Employee> findAll() {
	String sql = "select * from employee;";
	
	RowMapper<Employee> map = new RowMapper<Employee> () {

		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee em = new Employee();
			
			em.setEid(rs.getInt("eid"));
			em.setEname(rs.getString("ename"));
			
			return em;
		}
		
	};
	  
		return jdbc.query(sql, map);
	}

	

	
}
