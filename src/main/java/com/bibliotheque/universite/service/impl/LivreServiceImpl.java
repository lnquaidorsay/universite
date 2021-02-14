package com.bibliotheque.universite.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibliotheque.universite.entities.Livre;
import com.bibliotheque.universite.repository.LivreRepository;
import com.bibliotheque.universite.service.LivreService;
import com.bibliotheque.universite.shared.dto.LivreDto;

@Service
public class LivreServiceImpl implements LivreService {
	@Autowired
	private LivreRepository livreRepository;

	@Override
	public List<LivreDto> recupererTousLivres() {
		List<Livre> myList = livreRepository.findAll();
		List<LivreDto> listeResultat = new ArrayList<LivreDto>();
		for (int i = 0; i < myList.size(); i++) {
			ModelMapper modelMapper = new ModelMapper();
			LivreDto livreDto = modelMapper.map(myList.get(i), LivreDto.class);
			listeResultat.add(livreDto);
		}
		return listeResultat;
	}

	@Override
	public LivreDto sauvegarderLivre(LivreDto livre) {
		ModelMapper modelMapper = new ModelMapper();

		Livre livreEntity = modelMapper.map(livre, Livre.class);
		Livre newLivre = livreRepository.save(livreEntity);
		LivreDto livreDto = modelMapper.map(newLivre, LivreDto.class);
		return livreDto;
	}

	@Override
	public LivreDto recupererLivreParId(LivreDto livre) {
		ModelMapper modelMapper = new ModelMapper();
		Livre livreEntity = modelMapper.map(livre, Livre.class);
		// Livre existLivre = livreRepository.chercherLivreParId(livreEntity.getId());
		Livre existLivre = livreRepository.findById(livreEntity.getId()).get();
		LivreDto livreDto = modelMapper.map(existLivre, LivreDto.class);
		return livreDto;
	}

	@Override
	public LivreDto miseAjourLivre(LivreDto livre) {
		LivreDto livreToMaj = recupererLivreParId(livre);
		livreToMaj.setAuteur(livre.getAuteur());
		livreToMaj.setTitre(livre.getTitre());
		livreToMaj.setIsbn(livre.getIsbn());
		livreToMaj.setDatePublication(livre.getDatePublication());
		livreToMaj.setDateEnregistrement(LocalDate.now());
		livreToMaj.setNbExemplaires(livre.getNbExemplaires());
		livreToMaj.setCategorie(livre.getCategorie());
		return sauvegarderLivre(livreToMaj);
	}

	@Override
	public void supprimerLivre(Integer idLivre) {
		livreRepository.deleteById(idLivre);

	}

	@Override
	public List<LivreDto> chercherLivreParSonTitreOuPartieTitre(String titre) {
		// Livre livre = livreRepository.findbyTitre(titre);
		// ModelMapper modelMapper = new ModelMapper();
		// Livre livreEntity = modelMapper.map(livre, Livre.class);
		List<LivreDto> listeResultat = new ArrayList<LivreDto>();
		List<Livre> myList = livreRepository
				.findByTitreLikeIgnoreCase((new StringBuilder()).append("%").append(titre).append("%").toString());
		for (int i = 0; i < myList.size(); i++) {
			ModelMapper modelMapper = new ModelMapper();
			LivreDto livreDto = modelMapper.map(myList.get(i), LivreDto.class);
			listeResultat.add(livreDto);
		}

		return listeResultat;
	}

	@Override
	public LivreDto chercherLivreParIsbn(String isbn) {
		ModelMapper modelMapper = new ModelMapper();
		Livre livre = livreRepository.findByIsbnIgnoreCase(isbn);
		LivreDto livreDto = null;
		if (livre != null) {
			livreDto = modelMapper.map(livre, LivreDto.class);
		}
		return livreDto;
	}

	@Override
	public boolean verifierSiIdExist(Integer id) {
		return livreRepository.existsById(id);
	}

	@Override
	public List<LivreDto> recupererLivreParCategorie(String codeCategorie) {
		List<Livre> myList = livreRepository.findByCategorie(codeCategorie);
		List<LivreDto> listeResultat = new ArrayList<LivreDto>();
		for (int i = 0; i < myList.size(); i++) {
			ModelMapper modelMapper = new ModelMapper();
			LivreDto livreDto = modelMapper.map(myList.get(i), LivreDto.class);
			listeResultat.add(livreDto);
		}
		return listeResultat;
	}

	@Override
	public void supprimerUnLivre(LivreDto livre) {
		ModelMapper modelMapper = new ModelMapper();
		Livre livreDeleted = modelMapper.map(livre, Livre.class);
		livreRepository.deleteById(livreDeleted.getId());

	}

}
