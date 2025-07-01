package com.biblio.bibliotheque.model.pret;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Rendu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rendu;

    @Column(name = "date_du_rendu", nullable = false)
    private LocalDateTime dateDuRendu;

    @OneToOne
    @JoinColumn(name = "id_pret", nullable = false, unique = true)
    private Pret pret;

    // Getters and Setters
    public Integer getId_rendu() {
        return id_rendu;
    }

    public void setId_rendu(Integer id_rendu) {
        this.id_rendu = id_rendu;
    }

    public LocalDateTime getDateDuRendu() {
        return dateDuRendu;
    }

    public void setDateDuRendu(LocalDateTime dateDuRendu) {
        this.dateDuRendu = dateDuRendu;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }
}
