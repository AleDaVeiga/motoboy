package com.wgsistemas.motoboy.service;

import java.util.HashMap;
import java.util.Map;

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

import com.wgsistemas.motoboy.controller.admin.dominio.AdminCustomerReturn;
import com.wgsistemas.motoboy.mail.EmailHtmlSender;
import com.wgsistemas.motoboy.mail.EmailStatus;
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
	@Autowired
	private EmailHtmlSender emailHtmlSender;
	
	@Override
	public Customer newCustomer() {
		Customer customer = new Customer();
		Address customerAddress = new Address();
		customerAddress.setState(stateRepository.findByAbbreviation("SC"));
		customer.setCustomerAddress(customerAddress);
		return customer;
	}
	
	@Override
	public AdminCustomerReturn createCustomer(Customer customer, String username) {
		AdminCustomerReturn ret = newCustomerAccess(customer);
		ret.setCustomer(super.create(customer, username));
		return ret;
	}

	private AdminCustomerReturn newCustomerAccess(Customer customer) {
		AdminCustomerReturn ret = new AdminCustomerReturn();
		User customerAccess = customer.getCustomerAccess();
		if (StringUtils.isNotBlank(customerAccess.getUsername())) {
			String passwordDefault = "123motoboy";
			customerAccess.setPassword(passwordDefault);
			customerAccess.setEmail(customer.getFirstEmail());
			ret.setEmailStatus(sendEmailCreateCustomerAccess(customer));
			customer.setCustomerAccess(userService.save(customerAccess));
		} else {
			customer.setCustomerAccess(null);
			ret.setEmailStatus(new EmailStatus(customer.getFirstEmail(), "Cadastro de cliente", ""));
		}
		return ret;
	}

	private EmailStatus sendEmailCreateCustomerAccess(Customer customer) {
		if (customer.getCustomerAccess() != null && !customer.getNotBlankEmails().isEmpty() && customer.isEmailNotifications()) {
			Map<String, Object> context = new HashMap<>();
			context.put("title", "Cadastro de cliente");
			context.put("customer", customer);
			return emailHtmlSender.send(customer.getNotBlankEmails(), "Cadastro de cliente", "customer.ftl", context);
		}
		return new EmailStatus(customer.getNotBlankEmails().stream().toArray(String[]::new), "Cadastro de cliente", "");
	}
	
	@Override
	public AdminCustomerReturn updateCustomer(Customer customer) {
		AdminCustomerReturn ret = updateCustomerAccess(customer);
		ret.setCustomer(super.update(customer));
		return ret;
	}

	private AdminCustomerReturn updateCustomerAccess(Customer customer) {
		AdminCustomerReturn ret;
		Customer customerOld = customerRepository.findById(customer.getId());
		if(customerOld.getCustomerAccess() == null || NumberUtils.LONG_ZERO.compareTo(customerOld.getCustomerAccess().getId()) > 0) {
			ret = newCustomerAccess(customer);
		} else {
			ret = new AdminCustomerReturn();
			User customerAccess = customerOld.getCustomerAccess();
			customerAccess.setEmail(customer.getFirstEmail());
			customer.setCustomerAccess(userService.updateEmail(customerAccess));
			ret.setEmailStatus(new EmailStatus(customer.getFirstEmail(), "Cadastro de cliente", ""));
		}
		return ret;
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