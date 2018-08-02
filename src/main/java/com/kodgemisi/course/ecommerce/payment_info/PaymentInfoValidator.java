package com.kodgemisi.course.ecommerce.payment_info;

import org.springframework.lang.Nullable;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PaymentInfoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(PaymentInfo.class);
    }

    @Override
    public void validate(@Nullable Object target, Errors errors) {

    }

}
