package com.biblio.bibliotheque.model.pret;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class StatusProlongement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_status;

    @Column(name = "date_prolongement", nullable = false)
    private LocalDateTime dateProlongement;

    @ManyToOne
    @JoinColumn(name = "id_pret", nullable = false)
    private Pret pret;

    @ManyToOne
    @JoinColumn(name = "id_prolongement", nullable = false)
    private Prolongement prolongement;

    // Getters and Setters

    public Integer getId_status() {
        return id_status;
    }

    public void setId_status(Integer id_status) {
        this.id_status = id_status;
    }

    public LocalDateTime getDateProlongement() {
        return dateProlongement;
    }

    public void setDateProlongement(LocalDateTime dateProlongement) {
        this.dateProlongement = dateProlongement;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }

    public Prolongement getProlongement() {
        return prolongement;
    }

    public void setProlongement(Prolongement prolongement) {
        this.prolongement = prolongement;
    }
}
