package com.example.karthik.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ApplicationSecurity {

	@Bean
	public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
		
	return http.authorizeHttpRequests(auth->auth.anyRequest().authenticated())
			.formLogin(Customizer.withDefaults())
			.oauth2Login(Customizer.withDefaults())
	         .build();
		
		
	}
}
