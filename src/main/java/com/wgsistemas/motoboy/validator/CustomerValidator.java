package com.wgsistemas.motoboy.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.wgsistemas.motoboy.model.Customer;
import com.wgsistemas.motoboy.service.UserService;
import com.wgsistemas.motoboy.util.StringUtil;

@Component
public class CustomerValidator implements Validator {
	@Autowired
	private UserService userService;

	@Override
    public boolean supports(Class<?> aClass) {
        return Customer.class.equals(aClass);
    }

	@Override
	public void validate(Object obj, Errors errors) {
		Customer customer = (Customer) obj;

		if (customer.getCustomerAccess() != null) {
			if (StringUtils.isNotBlank(customer.getCustomerAccess().getUsername())) {
				if (customer.getCustomerAccess().getUsername().length() < 6 || customer.getCustomerAccess().getUsername().length() > 32) {
					errors.rejectValue("customerAccess.username", "userform.username.size");
				}
				if (userService.findByUsername(customer.getCustomerAccess().getUsername()) != null) {
					errors.rejectValue("customerAccess.username", "userform.username.duplicate");
				}
			}
		}
		for (String email : customer.getEmails()) {
			if (StringUtils.isNotBlank(email)) {
				if (!StringUtil.matchesEmailPattern(email)) {
					errors.rejectValue("email", "email.invalid");
				}
			}
		}
	}
}