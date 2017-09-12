package com.wgsistemas.motoboy.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wgsistemas.motoboy.model.User;
import com.wgsistemas.motoboy.service.UserService;

@Component
public class UserEmailValidator implements Validator {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private Pattern pattern;
	private Matcher matcher;
	
	@Autowired
	private UserService userService;
	
	public UserEmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	@Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "notempty");

		User userToChange = userService.findByUsername(user.getUsername());
		if (userToChange == null) {
			errors.rejectValue("username", "userform.username.notfound");
		}
		
		matcher = pattern.matcher(user.getEmail());
		if (!matcher.matches()) {
			errors.rejectValue("email", "userform.email.invalid");
		}
	}
}