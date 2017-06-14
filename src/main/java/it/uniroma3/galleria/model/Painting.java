package it.uniroma3.galleria.model;
import javax.persistence.*;

import javax.validation.constraints.*;

@Entity
public class Painting {
	
	public Painting() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String title;
	
	@NotNull
	private Integer year;
	
	@NotNull
	private String tecnique;
	 
	@NotNull
	@Size (min = 2)
	private String size;
	
	@ManyToOne
	private Artist author;
}
