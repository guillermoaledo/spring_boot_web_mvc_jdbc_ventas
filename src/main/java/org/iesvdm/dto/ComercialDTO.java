package org.iesvdm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComercialDTO {
	
	private int id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private double comision;
	
	private int id_cliente;
	private String nombre_cliente;
	private String apellido1_cliente;
	private String apellido2_cliente;
	private String ciudad_cliente;
	private int categoria_cliente;
}
