package com.kruger.barber.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kruger.barber.entities.HairCut;
import com.kruger.barber.repositories.HairCutRepository;

@Service
public class HairCutService {
	
	@Autowired
	private HairCutRepository repository;
	
	public List<HairCut> findAll() {
		return repository.findAll();
	}
	
	public HairCut findById(Long id) {
		Optional<HairCut> obj = repository.findById(id);
		return obj.get();
	}
}
