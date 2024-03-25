package com.cush.curso.springboot.app.springbootcrud.validation;

import org.springframework.stereotype.Component;

import com.cush.curso.springboot.app.springbootcrud.services.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class IsExistsDbValidation implements ConstraintValidator<isExistsDb, String>{

    private ProductService productService;

    public IsExistsDbValidation(ProductService productService){
        this.productService = productService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(productService == null){
            return true;
        }
        return !productService.existsBySku(value);
    }


}
