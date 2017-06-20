package it.uniroma3.galleria.controller;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.galleria.model.Artist;
import it.uniroma3.galleria.model.Painting;
import it.uniroma3.galleria.repository.PaintingRepository;
import it.uniroma3.galleria.service.ArtistService;
import it.uniroma3.galleria.service.PaintingService;

@Controller
public class PaintingController {
	
	@Autowired
	private PaintingService pService;
	
	@Autowired
	private ArtistService aService;

	@GetMapping(value="/paintingList")
	public String paintingList(Model model) {
	model.addAttribute("paintings", pService.findAllPaintings());
	return "paintings";
	}

	
	@GetMapping(value="/newPainting")
	public String getPaintingCreationPage(Model model) {
		model.addAttribute("artists", aService.findAllArtists());
		model.addAttribute("painting", new Painting());
		return "insertPainting";
	}
	
	/*Gestire Errori. */
	
	@PostMapping(value="/newPainting")
	public String createPainting(@Valid @ModelAttribute Painting painting,BindingResult errors, Model model,
		@RequestParam(value="artist") Long artistId) {
		if (errors.hasErrors()) 
			return "adminOperations";
	
		else {
			Artist artist = aService.findById(artistId);
			painting.setArtist(artist);
			model.addAttribute("artist",artist);
			model.addAttribute("painting",painting);
			pService.addPainting(painting);
			return "painting";
		}
			
	}
	
	/* Implementare la ricerca attraverso diversi filtri (attributi di painting) */
	@GetMapping(value="/findPainting")
	public String getResearchPaintingPage() {
		return "paintingResearch";
	}
	
	/*
	@PostMapping(value="/findPainting")
	public String findPainting(@RequestParam(value="attribute") String searchAtt, @RequestParam(value="value") String attValue,
		 Model model) {
		try {
		Method findByAttribute = PaintingRepository.class.getMethod("findBy"+searchAtt, String.class);
		List<Painting> paintings = (List<Painting>)findByAttribute.invoke(pService, attValue);
		model.addAttribute("paintings",paintings);
		}
		catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | IllegalArgumentException e){
			return "paintingResearch";
		}
		return "paintings"; 
	} */
	
	
	@PostMapping(value = "/findPainting")
	public String findPainting(@RequestParam(value = "attribute") String attribute, @RequestParam(value = "value") String attValue,Model model)  {
		List<Painting> paintings = new ArrayList<Painting>();
		
		switch(attribute) {
	
			case "Title": paintings = pService.findByTitle(attValue);
			break;
		
			case "Tecnique": paintings = pService.findByTecnique(attValue);
			break;
		
			case "Year": paintings = pService.findByYear(Integer.parseInt(attValue));
			break;
		
			case "Artist":
				model.addAttribute("artists", aService.findByFirstname(attValue));
				return "artists";		
			
			default: model.addAttribute("ErrorResearch","Ricontrolla i campi e riprova"); 
			return "paintingResearch";
		
		}
		
		model.addAttribute("paintings",paintings);
		return "paintings";
	}
	
	@GetMapping(value="/paintingDetails") 
	public String getPaintingDetailsPage(@ModelAttribute("id") Long id, BindingResult errors, Model model) {
		if ( errors.hasErrors()) {
			return "index";
		}
		model.addAttribute("painting",pService.findById(id));
		return "painting";
	}
	
	@PostMapping(value="/deletePainting")
	public String deletePainting(@RequestParam("id") Long id, Model model){
		pService.deletePainting(pService.findById(id));
		model.addAttribute("paintings", pService.findAllPaintings());
		return "paintings";
	}
	
	
	
}
