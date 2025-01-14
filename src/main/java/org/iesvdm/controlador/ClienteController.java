package org.iesvdm.controlador;

import java.util.List;

import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.mapstruct.ComercialMapper;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.service.ClienteService;
import org.iesvdm.service.ComercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.validation.Valid;

@Controller
//Se puede fijar ruta base de las peticiones de este controlador.
//Los mappings de los métodos tendrían este valor /clientes como
//prefijo.
//@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ComercialService comercialService;
	
	@Autowired
	private ComercialMapper comercialMapper;
	
	
	//@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	//equivalente a la siguiente anotación
	@GetMapping("/clientes") //Al no tener ruta base para el controlador, cada método tiene que tener la ruta completa
	public String listar(Model model) {
		
		List<Cliente> listaClientes =  clienteService.listAll();
		model.addAttribute("listaClientes", listaClientes);
				
		return "clientes";
		
	}
	
	@GetMapping("/clientes/{id}")
	public String detalle(Model model, @PathVariable Integer id) {
		
		Cliente cliente = clienteService.one(id);
		model.addAttribute("cliente", cliente);
		
		List<Comercial> comerciales = comercialService.listByClientId(id);
		
		List<ComercialDTO> comercialesDTO = comerciales.stream().map(c -> comercialMapper.comercialAComercialDTO(c, comercialService.pedidos(c.getId()), comercialService.pedidosTrimestre(c.getId()), comercialService.pedidosSemestre(c.getId()), comercialService.pedidosAnio(c.getId()), comercialService.pedidosLustro(c.getId()))).toList();
		model.addAttribute("listaComerciales", comercialesDTO);
		
		return "detalle-cliente";
	}
	
	@GetMapping({"/clientes/crear","/clientes/crear/"})
	public String crear(@ModelAttribute Cliente cliente, Model model) {
		
		return "crear-cliente";
	}
	
	@PostMapping({"/clientes/crear","/clientes/crear/"})
	public String submitCrear(@Valid @ModelAttribute Cliente cliente, BindingResult bindingResulted, Model model) {
		
		if(bindingResulted.hasErrors()) {
			model.addAttribute("cliente", cliente);
			
			return "crear-cliente";
			
		}
		
		clienteService.newCliente(cliente);
		return "redirect:/clientes";
	}
	
	@GetMapping("/clientes/editar/{id}")
	public String editar(Model model, @PathVariable Integer id) {
		
		Cliente cliente = clienteService.one(id);
		model.addAttribute("cliente", cliente);
		
		return "editar-cliente";
	}
	
	@PostMapping("/clientes/editar/{id}")
	public String submitEditar(@Valid @ModelAttribute Cliente cliente, BindingResult bindingResulted, Model model) {
		
		if(bindingResulted.hasErrors()) {
			model.addAttribute("cliente", cliente);
			
			return "editar-cliente";
			
		}
		
		clienteService.replaceCliente(cliente);
		return "redirect:/clientes";
	}
	
	@PostMapping("/clientes/borrar/{id}")
	public RedirectView submitBorrar(@PathVariable int id) {
		
		clienteService.deleteCliente(id);
		
		return new RedirectView("/clientes");
	}
	
	
	
	

}