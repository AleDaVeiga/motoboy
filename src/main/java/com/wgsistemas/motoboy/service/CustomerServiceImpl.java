package com.wgsistemas.motoboy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(readOnly=true)
	public Customer findOne(Long id) {
		return customerRepository.findOne(id);
	}
	
	@Transactional(readOnly=true)
	public Iterable<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Transactional(readOnly=true)
	public Page<Customer> findAllByPage(String search, Pageable pageable) {
		if (search == null || search.trim().isEmpty()) {
			return customerRepository.findAll(pageable);
		}
		return customerRepository.findByFullNameContainingIgnoreCase(search, pageable);
	}
}