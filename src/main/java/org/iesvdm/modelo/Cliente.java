package org.iesvdm.modelo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
//La anotación @Data de lombok proporcionará el código de: 
//getters/setters, toString, equals y hashCode
//propio de los objetos POJOS o tipo Beans
@Data
//Para generar un constructor con lombok con todos los args
@AllArgsConstructor
@SuppressWarnings("unused")
public class Cliente {
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	private int id;
	
	@NotBlank(message = "Por favor, introduzca nombre")
	@Size(max=30, message = "Nombre como máximo de 30 caracteres")
	private String nombre;
	
	@NotBlank(message = "Por favor, introduzca apellido")
	@Size(max=30, message = "Nombre como máximo de 30 caracteres")
	private String apellido1;
	
	private String apellido2;
	
	@NotBlank(message = "Por favor, introduzca ciudad")
	@Size(max=50, message = "Nombre como máximo de 50 caracteres")
	private String ciudad;
	
	@Min(100)
	@Max(1000)
	private int categoria;
	
}
