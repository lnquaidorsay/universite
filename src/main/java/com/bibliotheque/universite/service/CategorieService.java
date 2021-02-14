package com.bibliotheque.universite.service;

import java.util.List;

import com.bibliotheque.universite.entities.Categorie;

public interface CategorieService {
	public List<Categorie> recupererToutesCategories();

	public Categorie chercherCategorieParCodeCat(String code);
}
