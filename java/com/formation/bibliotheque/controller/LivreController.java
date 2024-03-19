package com.formation.bibliotheque.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.bibliotheque.exception.ResourceNotFoundException;
import com.formation.bibliotheque.model.Livre;
import com.formation.bibliotheque.repository.LivreRepository;

@RestController
@RequestMapping("/api/livres")
public class LivreController {
	
	@Autowired
	private LivreRepository livreRepository;
	
	@GetMapping
	public List<Livre> getAllLivres(){
		return livreRepository.findAll();	
	}
	
	@PostMapping
	public Livre createLivre(@RequestBody Livre livre) {
		return livreRepository.save(livre);
	}
	
	@PutMapping
	public Livre uptadeLivre(@PathVariable(value="id") long livreId, @RequestBody Livre livreDetails) {
		Livre livre = livreRepository.findById(livreId)
				.orElseThrow(()-> new ResourceNotFoundException("Livre not found for this id : " + livreId));
		livre.setTitre(livreDetails.getTitre());
		livre.setAuteur(livreDetails.getAuteur());
		livre.setAnnee(livreDetails.getAnnee());
		final Livre updatedLivre = livreRepository.save(livre);
		return updatedLivre;
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteLivre(@PathVariable(value="id") long livreId){
		Livre livre = livreRepository.findById(livreId)
				.orElseThrow(()-> new ResourceNotFoundException("Livre not found for this id : " + livreId));
	livreRepository.delete(livre);
	Map<String, Boolean> response = new HashMap<>();
	response.put("deleted", Boolean.TRUE);
	return response;
	}
}
