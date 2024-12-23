package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain sfc(HttpSecurity http) throws Exception {
		
	return	http.authorizeHttpRequests(auth ->auth.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults())
			.formLogin(Customizer.withDefaults())
			.build();
		
	}
//
//	@Bean
//	public SecurityFilterChain sfc(HttpSecurity http) throws Exception {
//		
//		return http.oauth2Login(Customizer.withDefaults())
//		       .build();
//		
//	}

}
