package org.iesvdm.dao;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ComercialDAOImpl implements ComercialDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void create(Comercial comercial) {
		String sqlInsert = """
				INSERT INTO comercial (nombre, apellido1, apellido2, comisión)
				VALUES  (     			 ?,         ?,         ?,       ?)
				  """;

		KeyHolder keyHolder = new GeneratedKeyHolder();

		int rows = jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] { "id" });
			int idx = 1;
			ps.setString(idx++, comercial.getNombre());
			ps.setString(idx++, comercial.getApellido1());
			ps.setString(idx++, comercial.getApellido2());
			ps.setDouble(idx, comercial.getComision());
			return ps;
		}, keyHolder);

		comercial.setId(keyHolder.getKey().intValue());

		log.info("Insertados {} registros.", rows);

	}

	@Override
	public List<Comercial> getAll() {

		List<Comercial> listCom = jdbcTemplate.query("SELECT * FROM comercial",
				(rs, rowNum) -> new Comercial(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido1"),
						rs.getString("apellido2"), rs.getDouble("comisión")));

		log.info("Devueltos {} registros.", listCom.size());

		return listCom;
	}
	
	@Override
	public List<Comercial> getAllBy(int id) {
		List<Comercial> listCom = jdbcTemplate.query("select distinct co.* from comercial co left join (pedido p left join cliente cli on p.id_cliente = cli.id) on co.id = p.id_comercial where cli.id = ?", 
																(rs, rowNum) -> new Comercial(rs.getInt("id"), 
																		rs.getString("nombre"), 
																		rs.getString("apellido1"),
																		rs.getString("apellido2"),
																	rs.getDouble("comisión")),
																id);
		
		return listCom;
	}

	@Override
	public Optional<Comercial> find(int id) {

		Comercial com = jdbcTemplate.queryForObject("SELECT * FROM comercial WHERE id = ?",
				(rs, rowNum) -> new Comercial(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido1"),
						rs.getString("apellido2"), rs.getDouble("comisión")),
				id);
		if (com != null) {
			return Optional.of(com);
		}
		return Optional.empty();
	}

	@Override
	public void update(Comercial comercial) {
		int rows = jdbcTemplate.update("""
				UPDATE comercial SET
								nombre = ?,
								apellido1 = ?,
								apellido2 = ?,
								comisión = ?
						WHERE id = ?
				""", comercial.getNombre(), comercial.getApellido1(), comercial.getApellido2(), comercial.getComision(),
				comercial.getId());

		log.info("Update de Comercial con {} registros actualizados.", rows);

	}

	@Override
	public void delete(int id) {
		int rows = jdbcTemplate.update("DELETE FROM comercial WHERE id = ?", id);

		log.info("Delete de Comercial con {} registros eliminados.", rows);
	}

	@Override
	public Double totalPedidos(int id) {
		Double total = jdbcTemplate.queryForObject("SELECT sum(p.total) AS total FROM pedido p LEFT JOIN cliente c  ON p.id_cliente = c.id WHERE p.id_comercial = ?", 
				(rs, rowNum) -> (rs.getDouble("total")), id);
		return total;
	}
	
	@Override
	public Double mediaPedidos(int id) {
		Double media = jdbcTemplate.queryForObject("SELECT avg(p.total) as media FROM pedido p LEFT JOIN cliente c  ON p.id_cliente = c.id WHERE p.id_comercial = ?", 
				(rs, rowNum) -> (rs.getDouble("media")), id);
		return media;
	}
	
	@Override
	public Double pedidoMaximo(int id) {
		Double maximo = jdbcTemplate.queryForObject("SELECT max(p.total) as maximo FROM pedido p LEFT JOIN cliente c  ON p.id_cliente = c.id WHERE p.id_comercial = ?", 
				(rs, rowNum) -> (rs.getDouble("maximo")), id);
		return maximo;
	}
	
	@Override
	public Double pedidoMinimo(int id) {
		Double minimo = jdbcTemplate.queryForObject("SELECT min(p.total) as minimo FROM pedido p LEFT JOIN cliente c  ON p.id_cliente = c.id WHERE p.id_comercial = ?", 
				(rs, rowNum) -> (rs.getDouble("minimo")), id);
		return minimo;
	}

	@Override
	public int numPedidos(int id) {
		Integer pedidos = jdbcTemplate.queryForObject("select count(p.id) as pedidos from comercial c left join pedido p on p.id_comercial = c.id where c.id =  ?", 
				(rs, rowNum) -> (rs.getInt("pedidos")), id);
		return pedidos;
	}

	@Override
	public int numPedidosTrimestre(int id) {
		Integer pedidos = jdbcTemplate.queryForObject("select count(p.id) as pedidos from comercial c left join pedido p on p.id_comercial = c.id where c.id = ? and p.fecha BETWEEN DATE_SUB(NOW(), INTERVAL 3 MONTH) AND NOW()", 
				(rs, rowNum) -> (rs.getInt("pedidos")), id);
		return pedidos;
	}

	@Override
	public int numPedidosSemestre(int id) {
		Integer pedidos = jdbcTemplate.queryForObject("", 
				(rs, rowNum) -> (rs.getInt("pedidos")), id);
		return pedidos;
	}

	@Override
	public int numPedidosAnio(int id) {
		Integer pedidos = jdbcTemplate.queryForObject("", 
				(rs, rowNum) -> (rs.getInt("pedidos")), id);
		return pedidos;
	}

	@Override
	public int numPedidosLustro(int id) {
		Integer pedidos = jdbcTemplate.queryForObject("", 
				(rs, rowNum) -> (rs.getInt("pedidos")), id);
		return pedidos;
	}
	
	
}
