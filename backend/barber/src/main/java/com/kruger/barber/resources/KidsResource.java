package com.kruger.barber.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kruger.barber.entities.Kids;
import com.kruger.barber.services.KidsService;

@RestController
@RequestMapping(value = "/kids")
public class KidsResource {
	
	@Autowired 
	private KidsService service;
	
	@GetMapping
	public ResponseEntity<List<Kids>> findAll() {
		List<Kids> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	public ResponseEntity<Kids> findById(@PathVariable Long id){
		Kids obj = service.findById(id);
		return ResponseEntity.ok(obj);
	}
}
