package com.biblio.bibliotheque.service.livre;

import com.biblio.bibliotheque.model.livre.Exemplaire;
import com.biblio.bibliotheque.model.livre.EtatExemplaire;
import com.biblio.bibliotheque.repository.livre.ExemplaireRepository;
import com.biblio.bibliotheque.repository.livre.EtatExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExemplaireService {

    @Autowired
    private final ExemplaireRepository exemplaireRepository;

    public ExemplaireService(ExemplaireRepository exemplaireRepository) {
        this.exemplaireRepository = exemplaireRepository;
    }

    // Récupérer tous les exemplaires
    public List<Exemplaire> getAllExemplaires() {
        return exemplaireRepository.findAll();
    }

    // Récupérer un exemplaire par ID
    public Optional<Exemplaire> getExemplaireById(Integer id) {
        return exemplaireRepository.findById(id);
    }

    public EtatExemplaire findDernierEtatByExemplaireId(Integer id) {
        return exemplaireRepository.findDernierEtatByExemplaireId(id);
    }

    // Ajouter ou modifier un exemplaire
    public Exemplaire saveExemplaire(Exemplaire exemplaire) {
        return exemplaireRepository.save(exemplaire);
    }

    // Supprimer un exemplaire
    public void deleteExemplaire(Integer id) {
        exemplaireRepository.deleteById(id);
    }

    // Méthode pour récupérer les derniers états des exemplaires
    public List<EtatExemplaire> getExemplairesAvecDernierEtat() {
        return exemplaireRepository.getDernierEtatParExemplaire();
    }
}
