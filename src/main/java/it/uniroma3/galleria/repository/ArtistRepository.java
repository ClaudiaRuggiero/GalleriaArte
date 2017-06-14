package it.uniroma3.galleria.repository;
import java.util.*;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.galleria.model.Artist;

public interface ArtistRepository extends CrudRepository<Artist,Long> {

	public Artist findByName(String name);
	public Artist findBySurname(String surname);
	public List<Artist> findByNationality(String nationality);
}
