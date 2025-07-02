package com.biblio.bibliotheque.repository.pret;

import com.biblio.bibliotheque.model.pret.Pret;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PretRepository extends JpaRepository<Pret, Integer> {


}
