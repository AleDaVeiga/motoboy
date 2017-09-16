package com.wgsistemas.motoboy.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wgsistemas.motoboy.model.User;
import com.wgsistemas.motoboy.service.UserService;
import com.wgsistemas.motoboy.util.StringUtil;

@Component
public class UserRecoverValidator implements Validator {
	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "notempty");
		
		if (!StringUtil.matchesEmailPattern(user.getEmail())) {
			errors.rejectValue("email", "email.invalid");
		}
		
		User userToChange = userService.findByEmail(user.getEmail());
		if (userToChange == null) {
			errors.rejectValue("email", "message.userform.email.recover.invalid");
		}
	}
}