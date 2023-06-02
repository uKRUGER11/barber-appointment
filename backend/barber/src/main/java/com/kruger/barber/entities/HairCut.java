package com.kruger.barber.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "haircut")
public class HairCut implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant appointment;
	
	@ManyToMany
	@JoinTable(name = "tb_client_haircut", joinColumns = @JoinColumn(name = "tb_haircut"), 
	inverseJoinColumns = @JoinColumn(name = "client_id"))
	private Set<Client> clients = new HashSet<>();

	public HairCut() {
	}

	public HairCut(Long id, Instant appointment, Client client) {
		super();
		this.id = id;
		this.appointment = appointment;
		this.getClients();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getAppointment() {
		return appointment;
	}

	public void setAppointment(Instant appointment) {
		this.appointment = appointment;
	}

	public Set<Client> getClients() {
		return clients;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HairCut other = (HairCut) obj;
		return Objects.equals(id, other.id);
	}  
}
