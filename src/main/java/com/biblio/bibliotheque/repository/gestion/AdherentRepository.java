package com.biblio.bibliotheque.repository.gestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblio.bibliotheque.model.gestion.Adherent;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Integer> {
    @Override
    boolean existsById(Integer id_adherent);
}
