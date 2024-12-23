package com.example.karthik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.karthik.bean.Swift;
import com.example.karthik.configuration.HelloSpring;

@SpringBootApplication
public class CSpringAopApplication {

	public static void main(String[] args) {
		
	ApplicationContext context =	SpringApplication.run(CSpringAopApplication.class, args);

	

	Swift  swift = context.getBean(Swift.class);
	
	HelloSpring  hs = context.getBean(HelloSpring.class);
	
	System.out.println(hs.hns().CarModel());
	
//	System.out.println(swift.CarModel());
//	System.out.println(swift.CarName());
	
	((ConfigurableApplicationContext) context).close();
		
	}

}
