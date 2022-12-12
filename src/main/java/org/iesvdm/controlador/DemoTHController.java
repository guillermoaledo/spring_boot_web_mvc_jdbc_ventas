package org.iesvdm.controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoTHController {

	@GetMapping("/demoth")
	public String demoTH(Model model) {
		
		String textoParrafo2 = "Esto es el texto que irá dentro del párrafo...";
		model.addAttribute("parrafo2", textoParrafo2);
		
		
		List<String> books = new ArrayList<>();
		books.add("El Quijote");
		books.add("Crimen y Castigo");
		books.add("Oliver Twist");
		
		model.addAttribute(books); //Equivalente a model.addAttribute("books", books)
		
		List<String> listUsers = Arrays.asList("Paco González", "Pepe Domingo Castaño", "Manolo Lama"); 
		model.addAttribute("users", listUsers);
		

		
		return "demoth";
	}
	
}