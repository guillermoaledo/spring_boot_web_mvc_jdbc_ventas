package org.iesvdm.dao;

import java.util.List;

import org.iesvdm.dto.PedidoDTO;
import org.iesvdm.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PedidoDAOImpl implements PedidoDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<PedidoDTO> getAllBy(int id) {
		
		List<PedidoDTO> listPed = jdbcTemplate.query("SELECT p.*, c.nombre, c.apellido1, c.apellido2 FROM pedido p LEFT JOIN cliente c  ON p.id_cliente = c.id WHERE p.id_comercial = ?",
				(rs, rowNum) -> new PedidoDTO(rs.getInt("id"), rs.getDouble("total"), rs.getDate("fecha"),
						rs.getInt("id_cliente"), rs.getInt("id_comercial"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2")), id);

		log.info("Devueltos {} registros.", listPed.size());

		return listPed;

	}

}
