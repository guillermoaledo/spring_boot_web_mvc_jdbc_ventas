package org.iesvdm.service;

import java.util.List;
import java.util.Optional;

import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.dto.PedidoDTO;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComercialService {
	
	private ComercialDAO comercialDAO;
	
	@Autowired
	private PedidoDAO pedidoDAO;
	
	public ComercialService(ComercialDAO comercialDAO) {
		this.comercialDAO = comercialDAO;
	}
	
	public List<Comercial> listAll() {
		
		return comercialDAO.getAll();
	}
	
	public List<PedidoDTO> listarPedidos(int id) {
		
		return pedidoDAO.getAllBy(id);
	}
	
	public Comercial one(Integer id) {
		Optional<Comercial> optCom = comercialDAO.find(id);
		if(optCom.isPresent())
			return optCom.get();
		else
			return null;
	}
	
	public void newComercial(Comercial comercial) {
		
		comercialDAO.create(comercial);
	}
	
	public void replaceComercial(Comercial comercial) {
		
		comercialDAO.update(comercial);
	}
	
	public void deleteComercial(int id) {
		
		comercialDAO.delete(id);
	}
}
