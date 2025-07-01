package com.biblio.bibliotheque.repository.sanction;

import com.biblio.bibliotheque.model.sanction.Sanction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SanctionRepository extends JpaRepository<Sanction, Integer> {
    // Exemple :
    // List<Sanction> findByAdherentId(Integer idAdherent);
}
