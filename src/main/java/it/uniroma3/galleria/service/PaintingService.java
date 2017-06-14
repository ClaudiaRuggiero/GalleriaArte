package it.uniroma3.galleria.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import it.uniroma3.galleria.model.Artist;
import it.uniroma3.galleria.model.Painting;
import it.uniroma3.galleria.repository.PaintingRepository;

@Service
public class PaintingService {

	@Autowired
	private PaintingRepository paintingRepository;
	
	public List<Painting> findAll() {
		return this.paintingRepository.findAll();
	}
	
	@Transactional
	public void addPainting(Painting painting) {
		this.paintingRepository.save(painting);
	}
	
	public Painting findById(Long id) {
		return this.paintingRepository.findOne(id);
	}
	
	public List<Painting> findByAuthor(Artist author) {
		List<Painting> paintings = new ArrayList<Painting>();
		return paintings;
	}
}
