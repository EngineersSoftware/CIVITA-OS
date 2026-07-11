package co.gov.antioquia.civitas.civitas_os.validation;


import java.time.LocalDate;
import java.time.Period;

import jakarta.validation.ConstraintValidatorContext;

public class AdultUserValidator implements ConstrainValidator<AdultUser, LocalDate>{

    @Override
    public boolean isValid(LocalDate dateOfbirth, ConstraintValidatorContext context) {
        if(dateOfbirth == null){
            return false;
        }
       int age = Period.between(dateOfbirth, LocalDate.now()).getYears();
       return age >= 18;
    }

}
