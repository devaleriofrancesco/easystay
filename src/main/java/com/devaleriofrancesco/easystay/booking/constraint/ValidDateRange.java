package com.devaleriofrancesco.easystay.booking.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateRangeValidator.class)
@Documented
public @interface ValidDateRange {

    String message() default "Check out deve essere successivo a check in";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String checkInField() default "dataCheckIn";
    String checkOutField() default "dataCheckOut";

}
