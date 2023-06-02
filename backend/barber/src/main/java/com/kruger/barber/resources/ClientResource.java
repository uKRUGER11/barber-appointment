package com.kruger.barber.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kruger.barber.entities.Client;
import com.kruger.barber.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
@CrossOrigin
public class ClientResource {

	@Autowired
	private ClientService service;
	
	@GetMapping
	public ResponseEntity<List<Client>> findAll() {
		List<Client> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id) {
		Client obj = service.findById(id);
		return ResponseEntity.ok(obj);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Client> insert(@RequestBody Client obj) {
		obj = service.add(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PostMapping("/login") 
	public ResponseEntity<String> login(@RequestBody Client obj) {
		String phone = obj.getPhone();
        String password = obj.getPassword();
        
        Client client = service.auth(phone, password);

        if (client == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Telefone não cadastrado");
        }
        
        if (!client.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
        }
        return ResponseEntity.ok("Login realizado com sucesso");
	}
}
