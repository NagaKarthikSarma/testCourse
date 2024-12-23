package com.example.karthik.bean;

import org.springframework.stereotype.Component;

@Component
public class Honda implements CAR {

	@Override
	public String CarModel() {
		
		return "Gear";
	}

	@Override
	public String CarName() {
		
		return "HONDA -- U10";
	}

}
