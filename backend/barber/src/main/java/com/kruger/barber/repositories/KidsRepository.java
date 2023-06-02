package com.kruger.barber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kruger.barber.entities.Kids;

public interface KidsRepository extends JpaRepository<Kids, Long> {

}