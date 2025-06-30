package com.biblio.bibliotheque.service;

import com.biblio.bibliotheque.model.Utilisateur;
import com.biblio.bibliotheque.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Optional<Utilisateur> login(String username, String mdp) {
        return utilisateurRepository.findByUsernameAndMdp(username, mdp);
    }
}
