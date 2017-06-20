package it.uniroma3.galleria.repository;

import java.util.*;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.galleria.model.Artist;
import it.uniroma3.galleria.model.Painting;

public interface PaintingRepository extends CrudRepository<Painting,Long>{

	public List<Painting> findByYear(Integer year);
	public List<Painting> findByArtist(Artist author);
	public List<Painting> findByTitle(String title);
	public List<Painting> findBySize(String size);
	public List<Painting> findByTecnique(String tecnique);
	public List<Painting> findAll();
	
}
