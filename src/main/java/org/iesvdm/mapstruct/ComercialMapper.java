package org.iesvdm.mapstruct;

import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.modelo.Comercial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComercialMapper {

	@Mapping(target="id_cliente", source="id_clienteIn")
	@Mapping(target="nombre_cliente", source="nombre_clienteIn")
	@Mapping(target="apellido1_cliente", source="apellido1_clienteIn")
	@Mapping(target="apellido2_cliente", source="apellido2_clienteIn")
	@Mapping(target="ciudad_cliente", source="ciudad_clienteIn")
	@Mapping(target="categoria_cliente", source="categoria_clienteIn")
	public ComercialDTO comercialAComercialDTO(Comercial comercial, int id_clienteIn, String nombre_clienteIn, String apellido1_clienteIn, String apellido2_clienteIn, String ciudad_clienteIn, String categoria_clienteIn);
	
	public Comercial comercialDTOAComercial(ComercialDTO comercial);
}
