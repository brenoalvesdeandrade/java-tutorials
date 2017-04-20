package com.nklkarthi.spring.validator;

import com.nklkarthi.spring.form.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    @Override
    public boolean supports(final Class calzz) {
        return Person.class.isAssignableFrom(calzz);
    }

    @Override
    public void validate(final Object obj, final Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name");
    }
}