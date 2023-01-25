package org.iesvdm.validador;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RangoCategoriaValidator.class)
public @interface RangoCategoria {
	
	int[] value() default {
		100,200,300,400,500,600,700,800,900,1000
};
}
