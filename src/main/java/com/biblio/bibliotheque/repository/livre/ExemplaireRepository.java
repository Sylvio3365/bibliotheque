package com.biblio.bibliotheque.repository.livre;

import com.biblio.bibliotheque.model.livre.EtatExemplaire;
import com.biblio.bibliotheque.model.livre.Exemplaire;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
    @Query(value = """
                SELECT DISTINCT ON (ee.id_exemplaire) ee.*
                FROM etat_exemplaire ee
                ORDER BY ee.id_exemplaire, ee.date_modif DESC
            """, nativeQuery = true)
    List<EtatExemplaire> getDernierEtatParExemplaire();

    @Query(value = """
                SELECT ee.*
                FROM etat_exemplaire ee
                WHERE ee.id_exemplaire = :idExemplaire
                ORDER BY ee.date_modif DESC
                LIMIT 1
            """, nativeQuery = true)
    EtatExemplaire findDernierEtatByExemplaireId(@Param("idExemplaire") Integer idExemplaire);
}
