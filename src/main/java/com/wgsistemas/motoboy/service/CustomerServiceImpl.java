package com.wgsistemas.motoboy.service;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
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
		customer.setCustomerAccess(newCustomerAccess(customer.getCustomerAccess(), customer.getEmail()));
		return super.create(customer, username);
	}

	private User newCustomerAccess(User customerAccess, String customerEmail) {
		if (StringUtils.isNotBlank(customerAccess.getUsername())) {
			String passwordDefault = "123motoboy";
			customerAccess.setPassword(passwordDefault);
			customerAccess.setEmail(customerEmail);
			return userService.save(customerAccess);
		}
		return null;
	}
	
	@Override
	public Customer update(Customer customer) {
		updateCustomerAccess(customer);
		return super.update(customer);
	}

	private void updateCustomerAccess(Customer customer) {
		Customer customerOld = customerRepository.findById(customer.getId());
		if(customerOld.getCustomerAccess() == null || NumberUtils.LONG_ZERO.compareTo(customerOld.getCustomerAccess().getId()) > 0) {
			customer.setCustomerAccess(newCustomerAccess(customer.getCustomerAccess(), customer.getEmail()));
		} else {
			User customerAccess = customerOld.getCustomerAccess();
			customerAccess.setEmail(customer.getEmail());
			customer.setCustomerAccess(userService.updateEmail(customerAccess));
		}
	}

	@Override
	protected JpaRepository<Customer, Long> getRepository() {
		return customerRepository;
	}

	@Transactional
	public void remove(Customer customer) {
		if(customer.getCustomerAccess() != null) {
			userService.remove(customer.getCustomerAccess());
		}
		customerRepository.delete(customer);
	}

	@Transactional(readOnly=true)
	public Customer findOne(Long id) {
		return customerRepository.findOne(id);
	}
	
	@Transactional(readOnly=true)
	public Iterable<Customer> findAll(String username) {
		return customerRepository.findByOwner_Username(username, orderByFullNameAsc());
	}
	
	private Sort orderByFullNameAsc() {
        return new Sort(Direction.ASC, "fullName");
    }

	@Transactional(readOnly=true)
	public Page<Customer> findBySearchTerm(String search, String username, Pageable pageable) {
		if (search == null || search.trim().isEmpty()) {
			return customerRepository.findByOwner_Username(username, pageable);
		}
		return customerRepository.findByFullNameContainingIgnoreCaseAndOwner_Username(search, username, pageable);
	}
}