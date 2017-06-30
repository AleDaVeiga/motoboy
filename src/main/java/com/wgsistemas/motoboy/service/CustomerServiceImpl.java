package com.wgsistemas.motoboy.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wgsistemas.motoboy.model.Customer;
import com.wgsistemas.motoboy.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository deliveryManRepository;

	@Transactional
	public Customer create(Customer deliveryMan) {
		return deliveryManRepository.save(deliveryMan);
	}

	@Transactional
	public Customer update(Customer deliveryMan) {
		return deliveryManRepository.save(deliveryMan);
	}

	@Transactional
	public void remove(Customer deliveryMan) {
		deliveryManRepository.delete(deliveryMan);
	}

	@Transactional
	public Customer findOne(Long id) {
		return deliveryManRepository.findOne(id);
	}

	@Transactional
	public Iterable<Customer> findAll() {
		return deliveryManRepository.findAll();
	}
}