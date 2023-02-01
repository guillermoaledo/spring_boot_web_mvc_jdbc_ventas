package org.iesvdm.controlador;

import java.util.List;

import org.iesvdm.dto.PedidoDTO;
import org.iesvdm.excepcion.MiExcepcion;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.iesvdm.service.ComercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;



@Controller
public class ComercialController {
	
	@ExceptionHandler(MiExcepcion.class)
	public String handleMiExcepcion(Model model, MiExcepcion miExcepcion) {
		model.addAttribute("traza", miExcepcion.getMessage());
		
		return "error-mi-excepcion";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String handleAllUncaughtException(Model model, RuntimeException exception) {
		model.addAttribute("traza", exception.getMessage());
		
		return "error";
	}
	
	@GetMapping("/comerciales-runtime-exception")
	public String comercialRuntimeException() throws RuntimeException{
		throw new RuntimeException("Prueba de lanzamiento de excepción y manejo por ControllerAdvice");
	
	}
	
	@GetMapping("/comerciales-mi-excepcion")
	public String comercialMiExcepcion() throws MiExcepcion{
		throw new MiExcepcion();	
	}
	
	@Autowired
	private ComercialService comercialService;
	
	@GetMapping("/comerciales")
	public String listar(Model model) {
		
		List<Comercial> listaComerciales = comercialService.listAll();
		model.addAttribute("listaComerciales", listaComerciales);
		
		return "comerciales";
	}
	
	@GetMapping("/comerciales/{id}")
	public String detalle(Model model, @PathVariable Integer id) {
		
		Comercial comercial = comercialService .one(id);
		model.addAttribute("comercial", comercial);
		
		model.addAttribute("totalPedidos", comercialService.totalPedidos(id));
		
		model.addAttribute("mediaPedidos", comercialService.mediaPedidos(id));
		
		model.addAttribute("pedidoMaximo", comercialService.pedidoMaximo(id));
		
		model.addAttribute("pedidoMinimo", comercialService.pedidoMinimo(id));
		
		List<PedidoDTO> listPed = comercialService.listarPedidos(id);
		model.addAttribute("listaPedidos", listPed);
		
		List<PedidoDTO> listPedOrdenada = listPed.stream().sorted((p1, p2) -> Double.compare(p1.getTotal(), p2.getTotal())).toList();
		model.addAttribute("listaOrdenadaPedidos", listPedOrdenada);
		
		return "detalle-comercial";
	}
	
	@GetMapping({"/comerciales/crear","/comerciales/crear/"})
	public String crear(@ModelAttribute Comercial comercial, Model model) {
		
		return "crear-comercial";
	}
	
	@PostMapping({"/comerciales/crear","/comerciales/crear/"})
	public String submitCrear(@Valid @ModelAttribute Comercial comercial, BindingResult bindingResulted, Model model) {
		
		if(bindingResulted.hasErrors()) {
			model.addAttribute("comercial", comercial);
			
			return "crear-comercial";
			
		}
		
		comercialService.newComercial(comercial);
		return "redirect:/comerciales";
	}
	
	@GetMapping("/comerciales/editar/{id}")
	public String editar(Model model, @PathVariable Integer id) {
		
		Comercial comercial= comercialService.one(id);
		model.addAttribute("comercial", comercial);
		
		return "editar-comercial";
	}
	
	@PostMapping("/comerciales/editar/{id}")
	public String submitEditar(@Valid @ModelAttribute Comercial comercial, BindingResult bindingResulted, Model model) {
		
		if(bindingResulted.hasErrors()) {
			model.addAttribute("comercial", comercial);
			
			return "editar-comercial";
			
		}
		
		comercialService.replaceComercial(comercial);
		return "redirect:/comerciales";
	}
	
	@PostMapping("/comerciales/borrar/{id}")
	public RedirectView submitBorrar(@PathVariable int id) {
		
		comercialService.deleteComercial(id);
		
		return new RedirectView("/comerciales");
	}
	
	
	
}
