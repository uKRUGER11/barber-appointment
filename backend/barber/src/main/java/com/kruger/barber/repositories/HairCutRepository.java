package com.kruger.barber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kruger.barber.entities.HairCut;

public interface HairCutRepository extends JpaRepository<HairCut, Long> {

}