package org.iesvdm.modelo;

import jakarta.validation.constraints.Email;
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
	
	@NotBlank(message = "{error.nombre}")
	@Size(max=30, message = "{error.nombre.size.max}")
	private String nombre;
	
	@NotBlank(message = "{error.apellido1}")
	@Size(max=30, message = "{error.apellido1}")
	private String apellido1;
	
	private String apellido2;
	
	@Email(regexp="^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}", message = "{error.email}")
	private String email;
	
	@NotBlank(message = "{error.ciudad}")
	@Size(max=50, message = "{error.ciudad.size.max}")
	private String ciudad;
	
	@Min(100)
	@Max(1000)
	private int categoria;
	
}
