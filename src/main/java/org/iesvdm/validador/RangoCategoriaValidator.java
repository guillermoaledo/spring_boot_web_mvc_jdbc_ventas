package org.iesvdm.validador;

import java.util.ArrayList;
import java.util.Iterator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RangoCategoriaValidator implements ConstraintValidator<RangoCategoria, String> {

	private ArrayList<Integer> rangos;
	
	public void initialize(RangoCategoria constraintAnnotation) {
		for(int i = 0; i < constraintAnnotation.value().length; i++) {
			this.rangos.add(constraintAnnotation.value()[i]);
		}
	}
	
	@Override
	public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
		String mensajeError = null;
		
		if(object != null) object = object.trim();
		
		boolean isValid = false;
		
		return false;
	}

}
