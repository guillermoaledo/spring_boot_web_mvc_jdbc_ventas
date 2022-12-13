package org.iesvdm.controlador;

import java.util.List;

import org.iesvdm.modelo.Comercial;
import org.iesvdm.service.ComercialService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;



@Controller
public class ComercialController {
	
	private ComercialService comercialService;
	
	public ComercialController(ComercialService comercialService) {
		this.comercialService = comercialService;
	}
	
	@GetMapping("/comerciales")
	public String listar(Model model) {
		
		List<Comercial> listaComerciales = comercialService.listAll();
		model.addAttribute("listaComerciales", listaComerciales);
		
		return "comerciales";
	}
}
