package com.bibliotheque.universite.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibliotheque.universite.entities.Categorie;
import com.bibliotheque.universite.repository.CategorieRepository;
import com.bibliotheque.universite.service.CategorieService;

@Service
public class CategorieServiceImpl implements CategorieService {

	@Autowired
	private CategorieRepository categorieRepository;

	@Override
	public List<Categorie> recupererToutesCategories() {
		return categorieRepository.findAll();
	}

	@Override
	public Categorie chercherCategorieParCodeCat(String code) {
		return categorieRepository.findCategorieByCodeCategorie(code);
	}

}
