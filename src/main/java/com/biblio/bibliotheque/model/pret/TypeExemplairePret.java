package com.biblio.bibliotheque.model.pret;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import com.biblio.bibliotheque.model.livre.Exemplaire;
import com.biblio.bibliotheque.model.livre.Type;

@Entity
@IdClass(TypeExemplairePretId.class)
public class TypeExemplairePret {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_type", nullable = false)
    private Type type;

    // Getters and Setters
    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

// Classe Id composite pour TypeExemplairePret
class TypeExemplairePretId implements Serializable {
    private Integer exemplaire;
    private Integer type;

    public TypeExemplairePretId() {}

    public TypeExemplairePretId(Integer exemplaire, Integer type) {
        this.exemplaire = exemplaire;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeExemplairePretId)) return false;
        TypeExemplairePretId that = (TypeExemplairePretId) o;
        return Objects.equals(exemplaire, that.exemplaire) &&
               Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exemplaire, type);
    }
}

