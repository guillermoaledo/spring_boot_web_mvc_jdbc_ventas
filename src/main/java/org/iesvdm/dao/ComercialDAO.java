package org.iesvdm.dao;

import java.util.List;
import java.util.Optional;

import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;

public interface ComercialDAO {
	
	public void create(Comercial comercial);
	
	public List<Comercial> getAll();
	public Optional<Comercial>  find(int id);
	
	public Double totalPedidos(int id);
	
	public Double mediaPedidos(int id);
	
	public Double pedidoMaximo(int id);
	
	public Double pedidoMinimo(int id);
	
	public void update(Comercial comercial);
	
	public void delete(int id);
	
}
