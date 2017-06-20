package it.uniroma3.galleria.repository;
import java.util.*;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.galleria.model.Artist;

public interface ArtistRepository extends CrudRepository<Artist,Long> {

	public List<Artist> findByFirstname(String firstname);
	public List<Artist> findByLastname(String lastname);
	public List<Artist> findByNationality(String nationality);
	public List<Artist> findAll();
}
