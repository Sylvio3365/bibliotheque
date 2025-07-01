package com.biblio.bibliotheque.service.sanction;

import com.biblio.bibliotheque.model.sanction.Sanction;
import com.biblio.bibliotheque.repository.sanction.SanctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanctionService {

    private final SanctionRepository sanctionRepository;

    @Autowired
    public SanctionService(SanctionRepository sanctionRepository) {
        this.sanctionRepository = sanctionRepository;
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
    // public List<Sanction> getSanctionsByAdherentId(Integer idAdherent) {
    //     return sanctionRepository.findByAdherentId(idAdherent);
    // }
}
