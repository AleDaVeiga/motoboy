package com.wgsistemas.motoboy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wgsistemas.motoboy.controller.admin.dominio.AdminCustomerReturn;
import com.wgsistemas.motoboy.model.Customer;

public interface CustomerService {
	Customer newCustomer();
	
	AdminCustomerReturn createCustomer(Customer customer, String username);

	AdminCustomerReturn updateCustomer(Customer customer);

	void remove(Customer customer);

	Customer findOne(Long id);
	
	@Deprecated
	Iterable<Customer> findAll(String username);
	
	Page<Customer> findBySearchTerm(String search, String username, Pageable pageable);
}