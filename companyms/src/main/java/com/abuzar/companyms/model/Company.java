package com.abuzar.companyms.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="company")
public class Company {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	
	/*@JsonManagedReference
	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
	private List<Job> jobs;
	

	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
	private List<Review> reviews;
	*/
	
	public Company() {
		
	}
	
	public Company(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
