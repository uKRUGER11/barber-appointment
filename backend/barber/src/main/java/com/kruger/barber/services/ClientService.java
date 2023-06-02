package com.kruger.barber.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kruger.barber.entities.Client;
import com.kruger.barber.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public List<Client> findAll() {
		return repository.findAll();
	}
	
	public Client findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		return obj.get();
	}
	
	public Client add(Client obj){
		return repository.save(obj);
	}
	
	public Client auth(String phone, String password) {
		return repository.findByPhoneAndPassword(phone, password);
	}
}
