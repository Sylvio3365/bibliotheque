package com.biblio.bibliotheque.service.sanction;

import com.biblio.bibliotheque.model.gestion.Adherent;
import com.biblio.bibliotheque.model.sanction.Penalite;
import com.biblio.bibliotheque.model.sanction.PenaliteProfil;
import com.biblio.bibliotheque.model.sanction.Sanction;
import com.biblio.bibliotheque.repository.sanction.PenaliteProfilRepository;
import com.biblio.bibliotheque.repository.sanction.PenaliteRepository;
import com.biblio.bibliotheque.repository.sanction.SanctionRepository;
import com.biblio.bibliotheque.repository.gestion.AdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SanctionService {

    private final SanctionRepository sanctionRepository;
    private final AdherentRepository adherentRepository;
    private final PenaliteProfilRepository penaliteProfilRepository;
    private final PenaliteRepository penaliteRepository;

    @Autowired
    public SanctionService(SanctionRepository sanctionRepository, AdherentRepository adherentRepository, PenaliteRepository penaliteRepository, PenaliteProfilRepository penaliteProfilRepository) {
        this.sanctionRepository = sanctionRepository;
        this.adherentRepository = adherentRepository;
        this.penaliteProfilRepository = penaliteProfilRepository;
        this.penaliteRepository = penaliteRepository;
    }

    // Récupérer toutes les sanctions
    public List<Sanction> getAllSanctions() {
        return sanctionRepository.findAll();
    }

    // Récupérer une sanction par ID
    public Optional<Sanction> getSanctionById(Integer id) {
        return sanctionRepository.findById(id);
    }

    // Enregistrer ou mettre à jour une sanction
    public Sanction saveSanction(Sanction sanction) {
        return sanctionRepository.save(sanction);
    }

    // Supprimer une sanction par ID
    public void deleteSanction(Integer id) {
        sanctionRepository.deleteById(id);
    }

    // Méthode personnalisée (à activer si ajoutée au repository)
    public void SanctionnerAdherent(Integer num_adherent, LocalDateTime date_debut) throws Exception {

        if (!adherentRepository.existsById(num_adherent)) {
            throw new Exception("l'adhérent avec l'ID " + num_adherent + " n'existe pas.");
        }

        Adherent a = adherentRepository.findById(num_adherent).orElseThrow(() -> new Exception("l'adhérent est introuvable malgré la vérification."));
        
        int id_profil = a.getProfil().getId_profil();
        PenaliteProfil penaliteProfil  = penaliteProfilRepository.findById(id_profil).orElse(null);
        Penalite penalite = penaliteRepository.findById(penaliteProfil.getId_penalite_profil()).orElse(null);
        int nb_jour = penalite.getNb_jour_de_penalite();

        Sanction s = new Sanction();
        s.setAdherent(a);
        s.setDate_debut(date_debut);
        s.setDate_fin(date_debut.plusDays(nb_jour));
        
        try {
            sanctionRepository.save(s);    
        } catch (Exception e) {
             System.out.println("une erreur est survenue : " + e.getMessage());
            throw new Exception("impossible de sauvegarder la sanction.", e);
        }
    }
}
