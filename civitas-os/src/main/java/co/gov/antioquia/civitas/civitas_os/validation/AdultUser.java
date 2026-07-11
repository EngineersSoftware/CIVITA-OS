package co.gov.antioquia.civitas.civitas_os.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.persistence.Entity;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = AdultUserValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AdultUser {

    String message() default "El usuario debe ser mayor de 18 años para este registro";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
