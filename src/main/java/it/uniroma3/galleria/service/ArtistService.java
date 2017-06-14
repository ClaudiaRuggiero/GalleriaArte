package it.uniroma3.galleria.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.galleria.model.Artist;
import it.uniroma3.galleria.repository.ArtistRepository;

@Service
public class ArtistService {

	@Autowired
	private ArtistRepository artistRepository;
	
	public Iterable<Artist> findAll() {
		return this.artistRepository.findAll();
	}
	
	@Transactional 
	public void add(final Artist artist) {
		this.artistRepository.save(artist);
	}
	
	public Artist findById(Long id) {
		return this.artistRepository.findOne(id);
	}
	
}

