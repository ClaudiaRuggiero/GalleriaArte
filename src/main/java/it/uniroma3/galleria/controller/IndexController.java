package it.uniroma3.galleria.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

	
	@GetMapping(value="/")
	public String getIndexPage() {
		return "index";
	}
	
	@GetMapping(value="/login")
	public String getLoginAdminPage() {
		return "login";
	}
	

	
}
