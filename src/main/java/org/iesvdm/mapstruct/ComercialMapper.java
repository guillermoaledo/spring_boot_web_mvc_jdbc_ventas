package org.iesvdm.mapstruct;

import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.modelo.Comercial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComercialMapper {

	@Mapping(target="numero_pedidos", source="numero_pedidosIn")
	@Mapping(target="numero_pedidos_trimestre", source="numero_pedidos_trimestreIn")
	@Mapping(target="numero_pedidos_semestre", source="numero_pedidos_semestreIn")
	@Mapping(target="numero_pedidos_anio", source="numero_pedidos_anioIn")
	@Mapping(target="numero_pedidos_lustro", source="numero_pedidos_lustroIn")
	public ComercialDTO comercialAComercialDTO(Comercial comercial, int numero_pedidosIn, int numero_pedidos_trimestreIn, int numero_pedidos_semestreIn, int numero_pedidos_anioIn, int numero_pedidos_lustroIn);
	
	public Comercial comercialDTOAComercial(ComercialDTO comercial);
}
