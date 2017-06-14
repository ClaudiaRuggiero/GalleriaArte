package it.uniroma3.galleria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import it.uniroma3.galleria.service.PaintingService;

@Controller
public class IndexController {

	@Autowired
	private PaintingService pService;
	
	@GetMapping(value="/")
	public String getIndexPage() {
		return "index";
	}
	
	@GetMapping(value="/paintingDetails") 
	public String paintingDetails (@ModelAttribute("id") Long id, BindingResult errors, Model model) {
		if ( errors.hasErrors()) {
			return "index";
		}
		model.addAttribute("painting",pService.findById(id));
		return "painting";
	}
	
	@GetMapping(value="/loginAdmin")
	public String getLoginAdminPage() {
		return "login";
		
	}
	
	
}
