package com.biblio.bibliotheque.model.livre;

import jakarta.persistence.*;

@Entity
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_livre;

    @Column(nullable = false, length = 50)
    private String titre;

    @Column(length = 50)
    private String auteur;

    // Getters and Setters
    public Integer getId_livre() {
        return id_livre;
    }

    public void setId_livre(Integer id_livre) {
        this.id_livre = id_livre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
}
