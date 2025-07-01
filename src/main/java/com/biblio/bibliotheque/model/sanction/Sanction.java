package com.biblio.bibliotheque.model.sanction;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.biblio.bibliotheque.model.gestion.Adherent;


@Entity
public class Sanction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_sanction;

    @Column(nullable = false)
    private LocalDateTime date_debut;

    @Column(nullable = false)
    private LocalDateTime date_fin;

    @ManyToOne
    @JoinColumn(name = "id_adherent", nullable = false)
    private Adherent adherent;

    // Getters and Setters
    public Integer getId_sanction() {
        return id_sanction;
    }

    public void setId_sanction(Integer id_sanction) {
        this.id_sanction = id_sanction;
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
    
    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }
}
