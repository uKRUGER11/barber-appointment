package com.kruger.barber.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.kruger.barber.entities.Client;
import com.kruger.barber.entities.HairCut;
import com.kruger.barber.entities.Kids;
import com.kruger.barber.repositories.ClientRepository;
import com.kruger.barber.repositories.HairCutRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private HairCutRepository hairCutRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Client c1 = new Client(1L, "Eduardo", "979979", "54321");
		Client c2 = new Client(2L, "Fernanda", "929992", "12345");
		Client c3 = new Client(3L, "Gabriel", "929392", "11111");
		
		Kids k1 = new Kids(null, "Juninho", "979979", c1);
		c1.setChildren(k1);
		Kids k2 = new Kids(null, "Creusa", "929992", c2);
		c2.setChildren(k2);
		
		HairCut o1 = new HairCut(null, Instant.parse("2023-03-11T14:00:00Z"), c1);
		HairCut o2 = new HairCut(null, Instant.parse("2023-03-11T15:00:00Z"), c2);
		HairCut o3 = new HairCut(null, Instant.parse("2023-03-11T16:00:00Z"), c3); 
				
		clientRepository.saveAll(Arrays.asList(c1, c2, c3));
		hairCutRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		o1.getClients().add(c1);
		o2.getClients().add(c2);
		o3.getClients().add(c3);
				
		hairCutRepository.saveAll(Arrays.asList(o1, o2, o3));
	}
}
