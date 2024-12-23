package com.example.karthik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.example.karthik.model.Customer;
import com.example.karthik.model.Post;
import com.example.karthik.repo.CustomerRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import aj.org.objectweb.asm.TypeReference;

@Controller
public class CustomerController {
	
	@GetMapping("/customer")
	public String customer() {
		System.out.println("hello");
		return "customer";
		
	}
	
	// @GetMapping("/welcome")
@PostMapping("/welcome")
public String welcome() {
	System.out.println("welcome");
	return "welcome";
}

@Autowired
CustomerRepo repo;

@GetMapping("/get")
public List<Customer> getDetails(){
	
	return repo.findAll();
	
}

@PostMapping("/add")
public Customer add(@RequestBody Customer cs) {
	return repo.save(cs);
	
	
}
@GetMapping("/fetch-post")
public ResponseEntity<Post> fetchPost() {
    // Logic to fetch the post data
    
    return ResponseEntity.ok(getPostData());
}

private Post getPostData() {
    // Replace with your actual implementation to fetch the data
    // For example, using RestTemplate:
    RestTemplate restTemplate = new RestTemplate();
    Post post = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/10", Post.class);
    return post;
}

@GetMapping("/fetch-all")
public ResponseEntity<List<Post>> fetchall() {
    // Logic to fetch the post data
    
    return ResponseEntity.ok(getallData());
}
private List<Post> getallData() {
    // Replace with your actual implementation to fetch the data
    // For example, using RestTemplate:
    RestTemplate restTemplate = new RestTemplate();
    
   List<Post> posts =  restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/",List.class);
    
 
    return posts;
}


}
