package com.wgsistemas.motoboy.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wgsistemas.motoboy.model.User;
import com.wgsistemas.motoboy.service.UserService;

@Component
public class UserPasswordValidator implements Validator {
	@Autowired
	private UserService userService;
    @Autowired
    private Md5PasswordEncoder md5PasswordEncoder;

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
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordToChange", "notempty");
	        
	        if(!userToChange.getPassword().equals(md5PasswordEncoder.encodePassword(user.getPasswordToChange(), null))) {
	            errors.rejectValue("passwordToChange", "userform.passwordtochange.invalid");
	        }
	        
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "notempty");
	        
	        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
	            errors.rejectValue("password", "userform.password.size");
	        }
	
	        if (!user.getPasswordConfirm().equals(user.getPassword())) {
	            errors.rejectValue("passwordConfirm", "userform.passwordconfirm.diff");
	        }
		} else {
            errors.rejectValue("username", "userform.username.notfound");
		}
	}
}