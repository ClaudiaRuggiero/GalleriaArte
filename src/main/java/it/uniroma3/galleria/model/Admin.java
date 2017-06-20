package it.uniroma3.galleria.model;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Admin {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=1)
	// @Unique(value = Administrator.class, property ="username")
	private String username;
	
	@NotNull
	@Size (min=1)
	private String password;
	
	@NotNull
	@Size(min=1)
	private String email;
	
	@NotNull
	@Size(min=1)
	private String firstname;
	
	@NotNull 
	@Size(min=1)
	private String lastname;
	
	@NotNull
	@Size(min=1)
	private String role="ADMIN";

	public Admin() {
		
	}
	
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
