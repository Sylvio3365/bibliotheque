package com.biblio.bibliotheque.repository.sanction;

import com.biblio.bibliotheque.model.sanction.PenaliteProfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenaliteProfilRepository extends JpaRepository<PenaliteProfil, Integer> {
    // Par exemple :
    // List<PenaliteProfil> findByProfilId(Integer idProfil);
}
