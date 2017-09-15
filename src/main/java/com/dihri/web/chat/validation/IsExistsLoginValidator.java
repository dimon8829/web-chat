package com.dihri.web.chat.validation;


import com.dihri.web.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsExistsLoginValidator implements ConstraintValidator<IsExistsLogin,String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(IsExistsLogin isExistsLogin) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s==null) return true;
        return !userService.isExistsLogin(s);
    }
}
