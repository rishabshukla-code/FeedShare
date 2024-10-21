package com.mycompany.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mycompany.model.pojo.User;

@Component
public class UserValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        
        return (User.class.equals(type));
        
    }

    @Override
    public void validate(Object o, Errors errors) {
        
    	User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fname", "fname is empty", "first name cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lname", "lname is empty", "last name cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uname", "uname is empty", "username cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email is empty", "email cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pswd", "password is empty", "password cannot be empty");
        
        if(!user.getEmail().matches("^[a-zA-Z0-9_.+-]+@northeastern\\.edu$")) {
        errors.rejectValue("email", "email.invalid", "Email must be from @northeastern.edu domain");
        }
        
        if (!user.getPswd().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            errors.rejectValue("pswd", "password.invalid", "Password must contain at least 8 characters, including 1 uppercase letter, 1 lowercase letter, 1 digit, and 1 special character");
        }
        
        if (user.getFname().length() > 32) {
            errors.rejectValue("fname", "fname.toolong", "First name must not exceed 32 characters");
        }

        if (user.getLname().length() > 32) {
            errors.rejectValue("lname", "lname.toolong", "Last name must not exceed 32 characters");
        }

        if (user.getUname().length() > 32) {
            errors.rejectValue("uname", "uname.toolong", "Username must not exceed 32 characters");
        }

        if (user.getEmail().length() > 32) {
            errors.rejectValue("email", "email.toolong", "Email must not exceed 32 characters");
        }

        if (user.getPswd().length() > 32) {
            errors.rejectValue("pswd", "password.toolong", "Password must not exceed 32 characters");
        }
        
    }
    
}