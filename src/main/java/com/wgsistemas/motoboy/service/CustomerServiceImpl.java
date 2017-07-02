package com.wgsistemas.motoboy.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.wgsistemas.motoboy.model.Customer;
import com.wgsistemas.motoboy.repository.CustomerRepository;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long> implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	protected JpaRepository<Customer, Long> getRepository() {
		return customerRepository;
	}

	@Transactional
	public void remove(Customer deliveryMan) {
		customerRepository.delete(deliveryMan);
	}

	@Transactional
	public Customer findOne(Long id) {
		return customerRepository.findOne(id);
	}

	@Transactional
	public Iterable<Customer> findAll() {
		return customerRepository.findAll();
	}
}