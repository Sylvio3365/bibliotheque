package com.biblio.bibliotheque.model;

import jakarta.persistence.*;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_utilisateur;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String mdp;

    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
    private Role role;

    // Getters and Setters
    public Integer getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(Integer id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
