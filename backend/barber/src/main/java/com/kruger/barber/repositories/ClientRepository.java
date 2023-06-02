
package com.kruger.barber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kruger.barber.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Client findByPhoneAndPassword(String phone, String password);
}