package com.bibliotheque.universite.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bibliotheque.universite.entities.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Integer> {
	/*
	 * En général, toute méthode dans une classe Spring Data JPA qui n'est marquée
	 * d'aucune annotation et qui commence par le préfixe find ou findBy est prise
	 * en charge par ce dernier. Dans cet exemple, il génèrera à l'invocation de
	 * chacune de ces méthodes les requêtes suivantes :
	 * 
	 * findAll() : select t from T t ; findByXxxAndYyyIgnoreCase(String xXX, String
	 * yYYY) : select t from T t where lower(t.xXX) = lower(xXX) and lower(t.yYY) =
	 * lower(yYYY). La casse est ignorée lors de la comparaison sur les paramètres
	 * d'entrée.
	 */
	public Livre findByIsbnIgnoreCase(String isbn);

	public Livre findByTitre(String titre);

	public Optional<Livre> findById(Integer id);

	// @Query("SELECT b FROM Livre l WHERE l.id = ?1")
	// Livre chercherLivreParId(Integer id);

	public List<Livre> findByTitreLikeIgnoreCase(String title);

	@Query("SELECT b " + "FROM Livre b " + "INNER JOIN b.categorie cat " + "WHERE cat.codeCategorie = :code")
	public List<Livre> findByCategorie(@Param("code") String codeCategorie);
}
