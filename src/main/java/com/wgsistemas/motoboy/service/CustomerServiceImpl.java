package com.wgsistemas.motoboy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wgsistemas.motoboy.model.Customer;
import com.wgsistemas.motoboy.model.datatype.Address;
import com.wgsistemas.motoboy.repository.CustomerRepository;
import com.wgsistemas.motoboy.repository.StateRepository;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long> implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
    @Autowired
    private StateRepository stateRepository;
	
	@Override
	public Customer newCustomer() {
		Customer customer = new Customer();
		Address customerAddress = new Address();
		customerAddress.setState(stateRepository.findByAbbreviation("SC"));
		customer.setCustomerAddress(customerAddress);
		return customer;
	}

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
	public Page<Customer> findBySearchTerm(String search, Pageable pageable) {
		if (search == null || search.trim().isEmpty()) {
			return customerRepository.findAll(pageable);
		}
		return customerRepository.findByFullNameContainingIgnoreCase(search, pageable);
	}
}