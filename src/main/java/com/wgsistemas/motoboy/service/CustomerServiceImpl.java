package com.wgsistemas.motoboy.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wgsistemas.motoboy.model.Customer;
import com.wgsistemas.motoboy.model.User;
import com.wgsistemas.motoboy.model.datatype.Address;
import com.wgsistemas.motoboy.repository.CustomerRepository;
import com.wgsistemas.motoboy.repository.StateRepository;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long> implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
    @Autowired
    private StateRepository stateRepository;
	@Autowired
    private UserService userService;
	
	@Override
	public Customer newCustomer() {
		Customer customer = new Customer();
		Address customerAddress = new Address();
		customerAddress.setState(stateRepository.findByAbbreviation("SC"));
		customer.setCustomerAddress(customerAddress);
		return customer;
	}
	
	@Override
	public Customer create(Customer customer, String username) {
		customer.setCustomerAccess(newCustomerAccess(customer.getCustomerAccess()));
		return super.create(customer, username);
	}

	private User newCustomerAccess(User customerAccess) {
		if (StringUtils.isNotBlank(customerAccess.getUsername())) {
			String passwordDefault = "123motoboy";
			customerAccess.setPassword(passwordDefault);
			return userService.save(customerAccess);
		}
		return null;
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
		return customerRepository.findAll(orderByFullNameAsc());
	}
	
	private Sort orderByFullNameAsc() {
        return new Sort(Direction.ASC, "fullName");
    }

	@Transactional(readOnly=true)
	public Page<Customer> findBySearchTerm(String search, Pageable pageable) {
		if (search == null || search.trim().isEmpty()) {
			return customerRepository.findAll(pageable);
		}
		return customerRepository.findByFullNameContainingIgnoreCase(search, pageable);
	}
}