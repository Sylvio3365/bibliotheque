package com.biblio.bibliotheque.model.sanction;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.biblio.bibliotheque.model.gestion.Adherent;

@Entity
@Table(name = "Sanction")
public class Sanction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sanction")
    private Integer idSanction;

    @Column(name = "date_sanction", nullable = false)
    private LocalDateTime dateSanction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adherent", nullable = false)
    private Adherent adherent;

    // Getters et setters
    public Integer getIdSanction() {
        return idSanction;
    }

    public void setIdSanction(Integer idSanction) {
        this.idSanction = idSanction;
    }

    public LocalDateTime getDateSanction() {
        return dateSanction;
    }

    public void setDateSanction(LocalDateTime dateSanction) {
        this.dateSanction = dateSanction;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }
}
