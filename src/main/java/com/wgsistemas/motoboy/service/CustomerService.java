package com.wgsistemas.motoboy.service;

import com.wgsistemas.motoboy.model.Customer;

public interface CustomerService {
	Customer create(Customer customer, String username);

	Customer update(Customer customer, String username);

	void remove(Customer customer);

	Customer findOne(Long id);
	
	Iterable<Customer> findAll();

	Iterable<Customer> findAll(String search);
}