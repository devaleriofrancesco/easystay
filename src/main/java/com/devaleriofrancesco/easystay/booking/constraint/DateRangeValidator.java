package com.devaleriofrancesco.easystay.booking.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, Object> {

    private String checkInField;
    private String checkOutField;

    @Override
    public void initialize(ValidDateRange constraintAnnotation) {
        this.checkInField = constraintAnnotation.checkInField();
        this.checkOutField = constraintAnnotation.checkOutField();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            final Field checkInField = o.getClass().getDeclaredField(this.checkInField);
            final Field checkOutField = o.getClass().getDeclaredField(this.checkOutField);

            checkInField.setAccessible(true);
            checkOutField.setAccessible(true);

            final LocalDate checkIn = (LocalDate) checkInField.get(o);
            final LocalDate checkOut = (LocalDate) checkOutField.get(o);

            // check if checkIn is before checkOut
            return checkIn == null || checkOut == null || checkIn.isBefore(checkOut);

        } catch (Exception e) {
            return false;
        }
    }


}
