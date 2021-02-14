package com.bibliotheque.universite.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotheque.universite.entities.Categorie;
import com.bibliotheque.universite.requests.LivreRequest;
import com.bibliotheque.universite.responses.LivreResponse;
import com.bibliotheque.universite.service.CategorieService;
import com.bibliotheque.universite.service.LivreService;
import com.bibliotheque.universite.shared.dto.LivreDto;

@RestController
@RequestMapping("/rest/book/api")
public class LivreController {
	@Autowired
	private LivreService livreService;

	@Autowired
	private CategorieService categoryServiceImpl;

	@GetMapping("/allbooks")
	public ResponseEntity<List<LivreResponse>> recupererLivresTous() {
		List<LivreResponse> livresResponse = new ArrayList<>();

		List<LivreDto> livres = livreService.recupererTousLivres();

		for (LivreDto livreDto : livres) {

			ModelMapper modelMapper = new ModelMapper();
			LivreResponse livreResponse = modelMapper.map(livreDto, LivreResponse.class);

			livresResponse.add(livreResponse);
		}
		return new ResponseEntity<List<LivreResponse>>(livresResponse, HttpStatus.OK);

	}

	@RequestMapping(value = "/addBook2", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<LivreResponse> ajouterNouveauLivre(@RequestBody LivreRequest livreRequest) {
		LivreDto livreExist = livreService.chercherLivreParIsbn(livreRequest.getIsbn());
		ModelMapper modelMapper = new ModelMapper();
		LivreDto livreDto = modelMapper.map(livreRequest, LivreDto.class);
		if (livreExist != null) {
			return new ResponseEntity<LivreResponse>(HttpStatus.CONFLICT);
		}
		Categorie categorie = categoryServiceImpl
				.chercherCategorieParCodeCat(livreRequest.getCategorie().getCodeCategorie());
		livreDto.setCategorie(categorie);
		livreDto.setDateEnregistrement(LocalDate.now());
		LivreDto livreEnregistre = livreService.sauvegarderLivre(livreDto);
		LivreResponse livreResponse = modelMapper.map(livreEnregistre, LivreResponse.class);
		return new ResponseEntity<LivreResponse>(livreResponse, HttpStatus.OK);
	}

	@PutMapping("/updateBook2")
	public ResponseEntity<LivreResponse> modifierUnLivre(@RequestBody LivreRequest livreRequest) {
		ModelMapper modelMapper = new ModelMapper();
		LivreDto livreDto = modelMapper.map(livreRequest, LivreDto.class);
		Categorie categorie = categoryServiceImpl
				.chercherCategorieParCodeCat(livreRequest.getCategorie().getCodeCategorie());
		livreDto.setCategorie(categorie);
		LivreDto livreUpdate = livreService.miseAjourLivre(livreDto);
		LivreResponse livreResponse = modelMapper.map(livreUpdate, LivreResponse.class);
		return new ResponseEntity<LivreResponse>(livreResponse, HttpStatus.OK);
	}

	@DeleteMapping("/deleteBook/{bookId}")
	public ResponseEntity<String> supprimerUnLivre(@PathVariable Integer bookId) {

		livreService.supprimerLivre(bookId);

		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/deleteBook")
	public ResponseEntity<String> supprimerLivre(@RequestBody LivreRequest livreRequest) {
		ModelMapper modelMapper = new ModelMapper();
		LivreDto livreDto = modelMapper.map(livreRequest, LivreDto.class);
		livreService.supprimerUnLivre(livreDto);

		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

}
