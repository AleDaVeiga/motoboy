package com.wgsistemas.motoboy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wgsistemas.motoboy.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Iterable<Customer> findByOwner_Username(String username, Sort sort);
	
	Page<Customer> findByOwner_Username(String username, Pageable pageable);
	
	Page<Customer> findByFullNameContainingIgnoreCaseAndOwner_Username(String fullName, String username, Pageable pageable);
}