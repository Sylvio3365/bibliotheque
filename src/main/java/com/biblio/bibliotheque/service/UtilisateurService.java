package com.biblio.bibliotheque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblio.bibliotheque.model.gestion.Utilisateur;
import com.biblio.bibliotheque.repository.gestion.UtilisateurRepository;

import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Optional<Utilisateur> login(String username, String mdp) {
        return utilisateurRepository.findByUsernameAndMdp(username, mdp);
    }
}
