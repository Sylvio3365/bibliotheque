package com.biblio.bibliotheque.model.gestion;

import jakarta.persistence.*;

@Entity
@Table(name = "Statut_Adherent")
public class StatutAdherent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatutAdherent;

    @Column(nullable = false, unique = true, length = 20)
    private String nom;

    public Integer getIdStatutAdherent() {
        return idStatutAdherent;
    }

    public void setIdStatutAdherent(Integer idStatutAdherent) {
        this.idStatutAdherent = idStatutAdherent;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
