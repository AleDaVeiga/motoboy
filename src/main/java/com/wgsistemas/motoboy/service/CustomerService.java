package com.wgsistemas.motoboy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wgsistemas.motoboy.model.Customer;

public interface CustomerService {
	Customer create(Customer customer, String username);

	Customer update(Customer customer, String username);

	void remove(Customer customer);

	Customer findOne(Long id);
	
	@Deprecated
	Iterable<Customer> findAll();
	
	Page<Customer> findAllByPage(String search, Pageable pageable);
}