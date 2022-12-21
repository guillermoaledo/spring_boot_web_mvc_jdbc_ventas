package org.iesvdm.dto;

import java.sql.Date;

import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Pedido;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class PedidoDTO extends Pedido{
	
	private String nombre_cliente;
	private String apellido1_cliente;
	private String apellido2_cliente;

	public PedidoDTO(int id, double total, Date fecha, int id_cliente, int id_comercial, String nombre_cliente, String apellido1_cliente, String apellido2_cliente) {
		super(id, total, fecha, id_cliente, id_comercial);
		this.nombre_cliente = nombre_cliente;
		this.apellido1_cliente = apellido1_cliente;
		this.apellido2_cliente = apellido2_cliente;
	}
	
	
}
