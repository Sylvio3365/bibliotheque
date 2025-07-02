package com.biblio.bibliotheque.model.pret;

import com.biblio.bibliotheque.model.livre.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.biblio.bibliotheque.model.gestion.Adherent;

@Entity
public class Pret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pret;

    @Column(nullable = false)
    private LocalDateTime date_debut;

    @Column(nullable = false)
    private LocalDateTime date_fin;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "id_adherent", nullable = false)
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "id_type", nullable = false)
    private Type type;
    // Getters and Setters
    public Integer getId_pret() {
        return id_pret;
    }

    public void setId_pret(Integer id_pret) {
        this.id_pret = id_pret;
    }

    public LocalDateTime getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDateTime date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDateTime getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDateTime date_fin) {
        this.date_fin = date_fin;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


}