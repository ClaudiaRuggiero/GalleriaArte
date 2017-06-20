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
	
	public List<Painting> findAllPaintings() {
		return this.paintingRepository.findAll();
	}
	
	@Transactional
	public void addPainting(Painting painting) {
		this.paintingRepository.save(painting);
	}
	
	public Painting findById(Long id) {
		return this.paintingRepository.findOne(id);
	}
	
	public List<Painting> findByTitle(String title) {
		return this.paintingRepository.findByTitle(title);
	}
	
	public List<Painting> findByYear(Integer year) {
		return this.paintingRepository.findByYear(year);
	}
	
	public List<Painting> findByTecnique(String tecnique) {
		return this.paintingRepository.findByTecnique(tecnique);
	}
	
	public List<Painting> findBySize(String size) {
		return this.paintingRepository.findBySize(size);
	}
	
	public List<Painting> findByAuthor(Artist author) {
		return this.paintingRepository.findByArtist(author);
	}
	
	public void deletePainting(Painting p) {
		this.paintingRepository.delete(p.getId());
	}
	
}
