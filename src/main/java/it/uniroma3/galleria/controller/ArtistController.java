package it.uniroma3.galleria.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.galleria.model.Artist;
import it.uniroma3.galleria.model.Painting;
import it.uniroma3.galleria.repository.ArtistRepository;
import it.uniroma3.galleria.service.ArtistService;
import it.uniroma3.galleria.service.PaintingService;


@Controller
public class ArtistController {
	
	@Autowired
	private ArtistService aService;
	
	@Autowired
	private PaintingService pService;
	
	@GetMapping(value="/artistList")
	public String artistList(Model model) {
	model.addAttribute("artists", aService.findAllArtists());
	return "artists";
	}
	
	@GetMapping(value="/findArtist")
	public String getResearchAuthorPage(Model model) {
		model.addAttribute("artist", new Artist());
		return "artistResearch";
	}
	
	/*
	@PostMapping(value="/findArtist")
	public String findArtist(Model model, @RequestParam(value="attribute") String searchAtt,
			@RequestParam(value="value") String attValue) {
		try {
			Method searchArtist = ArtistRepository.class.getMethod("findBy"+searchAtt, String.class);
			List<Artist> artists = (List<Artist>)searchArtist.invoke(aService, attValue);
			model.addAttribute("artists", artists);
		}
		catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | IllegalArgumentException e) {
			return "artistResearch";
		}
		return "artists";
	}
	*/
	
	
	@PostMapping("/findArtist")
	public String findArtist(@RequestParam(value="attribute") String searchAtt,@RequestParam(value="value") String attValue, Model model) {
		List<Artist> artists = new ArrayList<Artist>();
		switch(searchAtt) {
		
			case "Firstname": 
				artists = aService.findByFirstname(attValue);
				break;
				
			case "Lastname":
				artists = aService.findByLastname(attValue);
				break;
			case "Nationality":
				artists = aService.findByNationality(attValue);
				break;
				
			case "PaintingTitle":
				for (Painting painting :pService.findByTitle(attValue) ) 
					artists.add(painting.getArtist());
				break;
			
			default: 
				model.addAttribute("ErrorResearch","Ricontrolla i campi e riprova"); return "artistResearch";
		}
		model.addAttribute("artists", artists);
		return "artists";
	}
	
	@GetMapping(value="/artistDetails")
	public String getArtistDetailsPage(@ModelAttribute("id") Long id, BindingResult errors,Model model) {
		if(errors.hasErrors()) {return "painting";}
		Artist artist = aService.findById(id);
		model.addAttribute("artist", artist);
		model.addAttribute("paintings",artist.getPaintings());
		return "artist";
	}
	
	
	@GetMapping(value="/newArtist")
	public String getArtistCreationPage(Model model) {
		model.addAttribute("artist", new Artist());
		return "insertArtist";
	}
	
	
	@PostMapping(value="/newArtist") 
		public String createArtist(@Valid @ModelAttribute Artist artist, BindingResult errors, Model model) {
		if (errors.hasErrors()) {
			return "insertArtist";
		}
		aService.addArtist(artist);
		model.addAttribute("artist", artist);
		return "artist";
		
	}

	@PostMapping(value="/deleteArtist")
	public String deleteArtist(@RequestParam("id") Long id, Model model) {
		aService.deleteArtist(aService.findById(id));
		model.addAttribute("artists", aService.findAllArtists());
		return "artists";
	}
}
