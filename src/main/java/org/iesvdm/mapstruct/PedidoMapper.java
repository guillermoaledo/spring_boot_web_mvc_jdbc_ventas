//package org.iesvdm.mapstruct;
//
//import org.iesvdm.dto.PedidoDTO;
//import org.iesvdm.modelo.Pedido;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(componentModel = "spring")
//public interface PedidoMapper {
//	
//	@Mapping(target="nombre_cliente", source="nombre_clienteIn")
//	@Mapping(target="apellido1_cliente", source="apellido1_clienteIn")
//	@Mapping(target="apellido2_cliente", source="apellido2_clienteIn")
//	public PedidoDTO pedidoAPedidoDTO(Pedido pedido, String nombre_clienteIn, String apellido1_clienteIn, String apellido2_clienteIn);
//
//	public Pedido pedidoDTOAPedido(PedidoDTO pedido); 
//
//}
