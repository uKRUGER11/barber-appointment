package com.kruger.barber.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kruger.barber.entities.Kids;
import com.kruger.barber.repositories.KidsRepository;

@Service
public class KidsService {
	
	@Autowired
	private KidsRepository repository;
	
	public List<Kids> findAll() {
		return repository.findAll();
	}
	
	public Kids findById(Long id) {
		Optional<Kids> obj = repository.findById(id);
		return obj.get();
	}
}
