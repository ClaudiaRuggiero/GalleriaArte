package it.uniroma3.galleria.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.galleria.model.Artist;
import it.uniroma3.galleria.repository.ArtistRepository;

@Service
public class ArtistService {

	@Autowired
	private ArtistRepository artistRepository;
	
	public List<Artist> findAllArtists() {
		return this.artistRepository.findAll();
	}
	
	@Transactional 
	public void addArtist(final Artist artist) {
		this.artistRepository.save(artist);
	}
	
	public Artist findById(Long id) {
		return this.artistRepository.findOne(id);
	}
	
	public List<Artist> findByFirstname(String firstname) {
		return this.artistRepository.findByFirstname(firstname);
	}
	
	public List<Artist> findByLastname(String lastname) {
		return this.artistRepository.findByLastname(lastname);
	}
	
	public List<Artist> findByNationality(String nationality) {
		return this.artistRepository.findByNationality(nationality);
	}
	
	public void deleteArtist(Artist artist) {
		this.artistRepository.delete(artist.getId());
	}
}

