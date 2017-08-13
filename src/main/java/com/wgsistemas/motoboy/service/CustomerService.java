package com.wgsistemas.motoboy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wgsistemas.motoboy.model.Customer;

public interface CustomerService {
	Customer newCustomer();
	
	Customer create(Customer customer, String username);

	Customer update(Customer customer);

	void remove(Customer customer);

	Customer findOne(Long id);
	
	@Deprecated
	Iterable<Customer> findAll(String username);
	
	Page<Customer> findBySearchTerm(String search, String username, Pageable pageable);
}