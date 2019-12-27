package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "aliment", schema = "volailledor", catalog = "")
public class AlimentEntity {
    private int idAli;
    private String nomAli;
    private String description;
    private BigDecimal prix;

    @Id
    @Column(name = "idAli", nullable = false)
    public int getIdAli() {
        return idAli;
    }

    public void setIdAli(int idAli) {
        this.idAli = idAli;
    }

    @Basic
    @Column(name = "nomAli", nullable = false, length = 15)
    public String getNomAli() {
        return nomAli;
    }

    public void setNomAli(String nomAli) {
        this.nomAli = nomAli;
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
    @Column(name = "prix", nullable = false, precision = 2)
    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlimentEntity that = (AlimentEntity) o;
        return idAli == that.idAli &&
                Objects.equals(nomAli, that.nomAli) &&
                Objects.equals(description, that.description) &&
                Objects.equals(prix, that.prix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAli, nomAli, description, prix);
    }
}
