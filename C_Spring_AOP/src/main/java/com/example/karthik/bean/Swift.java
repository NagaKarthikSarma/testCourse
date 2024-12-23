package com.example.karthik.bean;

import org.springframework.stereotype.Component;

@Component
public class Swift implements CAR{

	@Override
	public String CarModel() {

		return "Auto";
	}

	@Override
	public String CarName() {
	
		return "SWIFT -- S20";
	}

}
