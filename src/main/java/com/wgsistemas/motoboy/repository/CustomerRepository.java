package com.wgsistemas.motoboy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wgsistemas.motoboy.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Page<Customer> findByFullNameContainingIgnoreCase(String fullName, Pageable pageable);
}