package com.wgsistemas.motoboy.service;

import com.wgsistemas.motoboy.model.Customer;

public interface CustomerService {
	Customer create(Customer deliveryMan);

	Customer update(Customer deliveryMan);

	void remove(Customer deliveryMan);

	Customer findOne(Long id);

	Iterable<Customer> findAll();
}