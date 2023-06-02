package com.kruger.barber.entities;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
@RequestMapping(name = "kids")
public class Kids implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String kidName;
	private String parentPhone;
	
	@JsonIgnore
	@OneToOne
	@MapsId
	private Client parent;
	
	public Kids () {
	}
	
	public Kids(Long id, String kidName, String parentPhone, Client parent) {
		super();
		this.id = id;
		this.kidName = kidName;
		this.parentPhone = parentPhone;
		this.parent = parent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKidName() {
		return kidName;
	}

	public void setKidName(String kidName) {
		this.kidName = kidName;
	}

	public String getParentPhone() {
		return parentPhone;
	}

	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kids other = (Kids) obj;
		return Objects.equals(id, other.id);
	} 	
}
