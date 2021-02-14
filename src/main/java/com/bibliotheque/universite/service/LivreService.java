package com.bibliotheque.universite.service;

import java.util.List;

import com.bibliotheque.universite.shared.dto.LivreDto;

public interface LivreService {
	public List<LivreDto> recupererTousLivres();

	public LivreDto sauvegarderLivre(LivreDto livre);

	public LivreDto recupererLivreParId(LivreDto livre);

	public LivreDto miseAjourLivre(LivreDto livre);

	public void supprimerLivre(Integer idLivre);

	public void supprimerUnLivre(LivreDto livre);

	public List<LivreDto> chercherLivreParSonTitreOuPartieTitre(String titre);

	public LivreDto chercherLivreParIsbn(String isbn);

	public boolean verifierSiIdExist(Integer id);

	public List<LivreDto> recupererLivreParCategorie(String codeCategory);
}
