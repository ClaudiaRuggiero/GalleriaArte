package it.uniroma3.galleria.model;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Administrator {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotNull 
	// @Unique(value = Administrator.class, property ="username")
	private String username;
	
	@NotNull
	private String password;
	
}
