package com.example.karthik.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.karthik.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
