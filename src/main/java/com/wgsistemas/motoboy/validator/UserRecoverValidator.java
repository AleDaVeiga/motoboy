package com.wgsistemas.motoboy.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wgsistemas.motoboy.model.User;
import com.wgsistemas.motoboy.service.UserService;

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

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "notempty");
		if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
			errors.rejectValue("username", "userform.username.size");
		}
		User userToChange = userService.findByUsername(user.getUsername());
		if (userToChange != null) {
			if (userToChange.getEmail() == null || userToChange.getEmail().trim().isEmpty()) {
				errors.rejectValue("username", "message.userform.email.recover.notempty");
			}
		} else {
			errors.rejectValue("username", "userform.username.notfound");
		}
	}
}