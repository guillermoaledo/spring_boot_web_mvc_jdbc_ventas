package org.iesvdm.dto;

import java.sql.Date;

import org.iesvdm.modelo.Pedido;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class PedidoDTO extends Pedido{
	public PedidoDTO(Pedido pedido) {
		
		super(pedido.getId(), pedido.getTotal(), pedido.getFecha(), pedido.getId_cliente(), pedido.getId_comercial());
	}
}
