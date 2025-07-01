package com.biblio.bibliotheque.model.livre;

import jakarta.persistence.*;

@Entity
public class Restriction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRestriction;

    @Column(nullable = false)
    private Integer ageMin;

    public Integer getIdRestriction() {
        return idRestriction;
    }

    public void setIdRestriction(Integer idRestriction) {
        this.idRestriction = idRestriction;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }
}
