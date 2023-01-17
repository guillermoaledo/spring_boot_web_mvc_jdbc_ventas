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
	
	private int numero_pedidos;
	private int numero_pedidos_trimestre;
	private int numero_pedidos_semestre;
	private int numero_pedidos_anio;
	private int numero_pedidos_lustro;
}
