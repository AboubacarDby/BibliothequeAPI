package com.formation.bibliotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.bibliotheque.model.Livre;

public interface LivreRepository extends JpaRepository<Livre, Long> {

}
