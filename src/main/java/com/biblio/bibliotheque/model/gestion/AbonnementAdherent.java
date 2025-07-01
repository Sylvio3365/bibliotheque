package com.biblio.bibliotheque.model.gestion;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(AbonnementAdherentId.class)
public class AbonnementAdherent {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_adherent", nullable = false)
    private Adherent adherent;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_abonnement", nullable = false)
    private Abonnement abonnement;

    @Column(name = "date_de_payement", nullable = false)
    private LocalDateTime dateDePayement;

    // Getters and Setters
    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }

    public LocalDateTime getDateDePayement() {
        return dateDePayement;
    }

    public void setDateDePayement(LocalDateTime dateDePayement) {
        this.dateDePayement = dateDePayement;
    }
}

class AbonnementAdherentId implements Serializable {
    private Integer adherent;
    private Integer abonnement;

    public AbonnementAdherentId() {}

    public AbonnementAdherentId(Integer adherent, Integer abonnement) {
        this.adherent = adherent;
        this.abonnement = abonnement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbonnementAdherentId)) return false;
        AbonnementAdherentId that = (AbonnementAdherentId) o;
        return Objects.equals(adherent, that.adherent) &&
               Objects.equals(abonnement, that.abonnement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adherent, abonnement);
    }
}

