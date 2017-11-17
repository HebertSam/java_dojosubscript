package com.project.dojosubscriptions.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// Replace occurences of "YourModel" with a valid model
import com.project.dojosubscriptions.models.User;

@Component
public class UserValidator implements Validator{	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		User user = (User) object;
		
		if (!user.getPasswordConfirm().equals(user.getPassword())){
			errors.rejectValue("passwordConfirmation", "Match");
		}
	}
}
