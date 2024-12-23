package com.example.karthik.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.karthik.bean.CAR;
import com.example.karthik.bean.Honda;
import com.example.karthik.bean.Swift;

@Configuration
public class HelloSpring {
	
	@Autowired
	Swift swift;
	
//	@Autowired 
//	
//	Honda honda;

	
	@Bean
	public Honda  hns() {
		
		
	System.out.println(" ----------- "  +   	swift.CarModel()+"           "+ swift.CarName()+"  ------------");
	
		return new Honda();
		
		
	}
}
