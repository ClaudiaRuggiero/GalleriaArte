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
	@Max (2018)
	private Integer year;
	
	@NotNull
	private String tecnique;
	 
	@NotNull
	@Size (min = 2)
	private String size;
	
	@Column 
	private String imageUrl;
	
	@ManyToOne
	private Artist artist;

	
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTecnique() {
		return tecnique;
	}

	public void setTecnique(String tecnique) {
		this.tecnique = tecnique;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist author) {
		this.artist = author;
	}
	
	
	
}
