package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "race", schema = "volailledor", catalog = "")
public class RaceEntity {
    private int idRace;
    private String nom;
    private String description;
    private BigDecimal prixRace;

    @Id
    @Column(name = "idRace", nullable = false)
    public int getIdRace() {
        return idRace;
    }

    public void setIdRace(int idRace) {
        this.idRace = idRace;
    }

    @Basic
    @Column(name = "nom", nullable = false, length = 15)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "prix_race", nullable = true, precision = 2)
    public BigDecimal getPrixRace() {
        return prixRace;
    }

    public void setPrixRace(BigDecimal prixRace) {
        this.prixRace = prixRace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaceEntity that = (RaceEntity) o;
        return idRace == that.idRace &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(description, that.description) &&
                Objects.equals(prixRace, that.prixRace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRace, nom, description, prixRace);
    }
}
