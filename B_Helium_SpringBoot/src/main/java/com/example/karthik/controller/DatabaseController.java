package com.example.karthik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.karthik.model.Customer;
import com.example.karthik.repo.CustomerRepo;

@RestController
public class DatabaseController {
	
	@Autowired
	CustomerRepo rep;
	
	@GetMapping("/custall")
	public List<Customer> add() {
		
	List<Customer> cust = rep.findAll();
	System.out.println(cust);
	
	return cust;
		
	}
	
	

}
