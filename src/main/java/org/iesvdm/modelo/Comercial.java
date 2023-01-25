package org.iesvdm.modelo;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@SuppressWarnings("unused")
public class Comercial {
	public Comercial() {
		// TODO Auto-generated constructor stub
	}
	private int id;
	
	@NotBlank(message = "{error.nombre}")
	@Size(max=30, message = "{error.nombre.size.max}")
	private String nombre;
	
	@NotBlank(message = "{error.apellido1}")
	@Size(max=30, message = "{error.apellido1}")
	private String apellido1;
	
	private String apellido2;
	
	@DecimalMax(value="0.946", inclusive=true, message = "{error.comision}")
	@DecimalMin(value="0.276", inclusive=true, message = "{error.comision}")
	private double comision;
}
