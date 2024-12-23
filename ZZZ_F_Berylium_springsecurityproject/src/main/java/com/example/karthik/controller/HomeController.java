package com.example.karthik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/webapp")
public class HomeController {
	
	@RequestMapping("/users")
	public String getWebPage() {
		
		return "FirstWeb";
	}

}
