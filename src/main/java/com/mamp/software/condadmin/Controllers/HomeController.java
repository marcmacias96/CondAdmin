package com.mamp.software.condadmin.Controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/")
public class HomeController {
	@GetMapping(value="/")
	public String home(Model model) {
		model.addAttribute("framework", "Spring Boot");
		model.addAttribute("title", "Certificación II");				
		model.addAttribute("description", "CondAdmin");
		return "home";
	}
	@GetMapping(value="/login")
	public String login(@RequestParam(value = "error", required = false)String error, Principal principal, RedirectAttributes flash, Model model) {
		
		if(principal != null) {
			flash.addFlashAttribute("info", "The user has an active session");
			return "redirect:/";
		}
		
		if(error != null) {
			model.addAttribute("error", "User or Password incorrect");
		}
		
		return "home";
	}
	
}