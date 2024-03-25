package com.cush.curso.springboot.app.springbootcrud.validation;

import org.springframework.stereotype.Component;

import com.cush.curso.springboot.app.springbootcrud.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String>{

    private UserService userService;

    public ExistsByUsernameValidation(UserService userService){
        this.userService = userService;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if(userService == null){
            return true;
        }
        return !userService.existsByUsername(username);
    }

}
