package org.iesvdm.service;

import java.util.List;
import java.util.Optional;

import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	
	private ClienteDAO clienteDAO;
	
	private ComercialDAO comercialDAO;
	
	//Se utiliza inyección automática por constructor del framework Spring.
	//Por tanto, se puede omitir la anotación Autowired
	//@Autowired
	public ClienteService(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}
	
	public List<Cliente> listAll() {
		
		return clienteDAO.getAll();
		
	}
	
	public Cliente one(Integer id) {
		Optional<Cliente> optCli = clienteDAO.find(id);
		if(optCli.isPresent())
			return optCli.get();
		else
			return null;
	}
	
	public void newCliente(Cliente cliente) {
		
		clienteDAO.create(cliente);
	}
	
	public void replaceCliente(Cliente cliente) {
		
		clienteDAO.update(cliente);
	}
	
	public void deleteCliente(int id) {
		
		clienteDAO.delete(id);
	}
	
	public List<Comercial> listaComerciales(int id) {
		return comercialDAO.getAllBy(id);
	}
	

}