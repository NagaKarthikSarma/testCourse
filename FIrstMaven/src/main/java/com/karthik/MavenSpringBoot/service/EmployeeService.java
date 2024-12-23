package com.karthik.MavenSpringBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karthik.MavenSpringBoot.model.Employee;
import com.karthik.MavenSpringBoot.repo.EmployeeRepo;

@Component
public class EmployeeService {
 
	
	
	EmployeeRepo emr;
	
	
	
	public EmployeeRepo getEmr() {
		return emr;
	}


	@Autowired
	public void setEmr(EmployeeRepo emr) {
		this.emr = emr;
	}



	public void addEmployee(Employee e) {
		emr.save(e);
		System.out.println(e.getEid()+"------"+e.getEname());
		
		
	}


	public List<Employee> getEmployees() {
		
		return emr.findAll();
	}

}
