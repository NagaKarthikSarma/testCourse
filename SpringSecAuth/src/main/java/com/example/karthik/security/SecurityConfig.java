package com.example.karthik.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityfilter (HttpSecurity http) throws Exception {
		
		return http.authorizeHttpRequests(auth->auth      // .requestMatchers("regiser").permitAll()
				.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.formLogin(Customizer.withDefaults())
				
				.oauth2Login(Customizer.withDefaults())
				
				.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
	    return new BCryptPasswordEncoder(12); // Or new SCryptPasswordEncoder()
	    
	}
	@Bean
	public AuthenticationProvider authprov() {
		
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	provider.setUserDetailsService(uds());
	
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
		
		
		
	}

	
	@Bean
	public UserDetailsService  uds() {
		
		UserDetails user1 = User.withUsername("karthik")
                .password(passwordEncoder().encode("K@rthik"))
                .roles("USER") // Add roles if needed
                .build();
		
		return new InMemoryUserDetailsManager(user1);
		
		
	}
	
}
