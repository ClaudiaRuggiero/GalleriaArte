package it.uniroma3.galleria.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.galleria.model.Painting;
import it.uniroma3.galleria.service.PaintingService;

@Controller
public class PaintingController {
	
	@Autowired
	private PaintingService pService;

	@GetMapping(value="/paintingList")
	public String paintingList(Model model) {
	model.addAttribute("paintings", pService.findAll());
	return "paintings";
	}

	/*Gestire errori. inserisce nuova opera nel db. */
	@PostMapping(value="/newPainting")
	public String createPainting(@Valid Painting painting,BindingResult errors, Model model) {
		if (errors.hasErrors()) 
			return "insertPainting";
		else {
			Painting p = new Painting();
			pService.addPainting(p);
			model.addAttribute(p);
			// ritorna la pagina che racchiude i dati dell'opera inserita
			return "painting";
		}
			
	}
	
}
