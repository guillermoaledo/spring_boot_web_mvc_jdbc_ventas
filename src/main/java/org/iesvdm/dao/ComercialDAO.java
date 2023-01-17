package org.iesvdm.dao;

import java.util.List;
import java.util.Optional;

import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;

public interface ComercialDAO {
	
	public void create(Comercial comercial);
	
	public List<Comercial> getAll();
	public Optional<Comercial>  find(int id);
	
	public List<Comercial> getAllBy(int id);
	
	public Double totalPedidos(int id);
	
	public Double mediaPedidos(int id);
	
	public Double pedidoMaximo(int id);
	
	public Double pedidoMinimo(int id);
	
	public void update(Comercial comercial);
	
	public void delete(int id);
	
	public int numPedidos(int id);
	
	public int numPedidosTrimestre(int id);
	
	public int numPedidosSemestre(int id);
	
	public int numPedidosAnio(int id);
	
	public int numPedidosLustro(int id);
	
}
