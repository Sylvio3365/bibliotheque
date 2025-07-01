package com.biblio.bibliotheque.service.gestion;

import com.biblio.bibliotheque.model.gestion.Adherent;
import com.biblio.bibliotheque.repository.gestion.AdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdherentService {

    @Autowired
    private AdherentRepository adherentRepository;

    public List<Adherent> getAll() {
        return adherentRepository.findAll();
    }

    public Optional<Adherent> getById(Integer id) {
        return adherentRepository.findById(id);
    }

    public Adherent save(Adherent adherent) {
        return adherentRepository.save(adherent);
    }

    public void delete(Integer id) {
        adherentRepository.deleteById(id);
    }

    // Exemples méthodes personnalisées
    /*
    public Adherent findByNomAndPrenom(String nom, String prenom) {
        return adherentRepository.findByNomAndPrenom(nom, prenom);
    }

    public Adherent findByUtilisateurId(Integer idUtilisateur) {
        return adherentRepository.findByUtilisateurId(idUtilisateur);
    }
    */
}
