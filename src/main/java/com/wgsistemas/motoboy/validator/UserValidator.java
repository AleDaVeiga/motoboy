package com.wgsistemas.motoboy.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wgsistemas.motoboy.model.User;
import com.wgsistemas.motoboy.service.UserService;

@Component
public class UserValidator implements Validator {
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
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "userform.username.duplicate");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "notempty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "userform.password.size");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "userform.passwordconfirm.diff");
        }
	}
}