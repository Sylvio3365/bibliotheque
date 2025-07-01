package com.biblio.bibliotheque.repository.pret;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biblio.bibliotheque.model.pret.Pret;

public interface PretRepository extends JpaRepository<Pret, Integer> {
    @Override
    boolean existsById(Integer id_pret);

    @Query("SELECT COUNT(p) > 0 FROM Pret p WHERE p.id_pret = :idPret AND p.adherent.id_adherent = :idAdherent")
    boolean pretAppartientAdherent(@Param("idPret") Integer idPret, @Param("idAdherent") Integer idAdherent);

}
