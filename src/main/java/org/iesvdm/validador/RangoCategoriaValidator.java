package org.iesvdm.validador;

import java.util.ArrayList;
import java.util.Iterator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RangoCategoriaValidator implements ConstraintValidator<RangoCategoria, Integer> {

	private ArrayList<Integer> rangos = new ArrayList<>();
	
	public void initialize(RangoCategoria constraintAnnotation) {
		for(int i = 0; i < constraintAnnotation.value().length; i++) {
			this.rangos.add(constraintAnnotation.value()[i]);
		}
	}
	
	@Override
	public boolean isValid(Integer categoria, ConstraintValidatorContext constraintContext) {
		boolean isValid = false;
		String mensajeError = "{error.categoria}";
		
		if(rangos.contains(categoria)) {
			isValid = true;
		}
		
		if ( !isValid ) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate(mensajeError)
            .addConstraintViolation();
        }
		
		return isValid;
		
	}

}
