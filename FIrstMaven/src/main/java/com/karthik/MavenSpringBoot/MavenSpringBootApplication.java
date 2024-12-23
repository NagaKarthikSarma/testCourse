package com.karthik.MavenSpringBoot;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.karthik.MavenSpringBoot.model.Employee;
import com.karthik.MavenSpringBoot.repo.EmployeeRepo;
import com.karthik.MavenSpringBoot.service.EmployeeService;

@SpringBootApplication
public class MavenSpringBootApplication {

	public static void main(String[] args) {
		
SpringApplication.run(MavenSpringBootApplication.class, args);
		
//	ApplicationContext context=	SpringApplication.run(MavenSpringBootApplication.class, args);
//	EmployeeRepo er = context.getBean(EmployeeRepo.class);
//	Employee em1 = context.getBean(Employee.class);
//	
//	EmployeeService service = context.getBean(EmployeeService.class);
////
////	em1.setEid(4);
////	em1.setEname("Jack");
////	
////	service.addEmployee(em1);
////	
//	
//List<Employee> employees =	service.getEmployees();
//	
//
//System.out.println(employees);
//	
	
	
	}
	
	

}
