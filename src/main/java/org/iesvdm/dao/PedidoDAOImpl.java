package org.iesvdm.dao;

import java.util.List;

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
	public List<Pedido> getAllBy(int id) {
		
		List<Pedido> listPed = jdbcTemplate.query("SELECT * FROM pedido WHERE id_comercial = ?",
				(rs, rowNum) -> new Pedido(rs.getInt("id"), rs.getDouble("total"), rs.getDate("fecha"),
						rs.getInt("id_cliente"), rs.getInt("id_comercial")), id);

		log.info("Devueltos {} registros.", listPed.size());

		return listPed;

	}

}
