package com.biblio.bibliotheque.service.livre;

import com.biblio.bibliotheque.model.livre.Livre;
import com.biblio.bibliotheque.repository.livre.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {

    private final LivreRepository livreRepository;

    @Autowired
    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    // Récupérer tous les livres
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    // Récupérer un livre par ID
    public Optional<Livre> getLivreById(Integer id) {
        return livreRepository.findById(id);
    }

    // Ajouter ou modifier un livre
    public Livre saveLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    // Supprimer un livre
    public void deleteLivre(Integer id) {
        livreRepository.deleteById(id);
    }

    // (Optionnel) Rechercher les livres par titre (partiel, insensible à la casse)
    // public List<Livre> getLivresByTitre(String titre) {
    //     return livreRepository.findByTitreContainingIgnoreCase(titre);
    // }

    // (Optionnel) Rechercher les livres par auteur (partiel, insensible à la casse)
    // public List<Livre> getLivresByAuteur(String auteur) {
    //     return livreRepository.findByAuteurContainingIgnoreCase(auteur);
    // }
}
