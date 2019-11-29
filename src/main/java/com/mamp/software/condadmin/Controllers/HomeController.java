package com.mamp.software.condadmin.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class HomeController {
	@GetMapping(value="/")
	public String home(Model model) {
		model.addAttribute("framework", "Spring Boot");
		model.addAttribute("title", "Certificación II");				
		model.addAttribute("description", "Ejercicio práctico de la implementación de aplicaciones web con el lenguaje Java utlizando el patrón de diseño MVC y el framework Spring Boot");
		return "home";
	}
}