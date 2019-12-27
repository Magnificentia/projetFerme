package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "vaccin", schema = "volailledor", catalog = "")
public class VaccinEntity {
    private int idVac;
    private String nomVac;
    private String periode;
    private int qteVac;
    private int qtePoule;
    private String description;
    private BigDecimal prix;

    @Id
    @Column(name = "idVac", nullable = false)
    public int getIdVac() {
        return idVac;
    }

    public void setIdVac(int idVac) {
        this.idVac = idVac;
    }

    @Basic
    @Column(name = "nomVac", nullable = false, length = 15)
    public String getNomVac() {
        return nomVac;
    }

    public void setNomVac(String nomVac) {
        this.nomVac = nomVac;
    }

    @Basic
    @Column(name = "periode", nullable = false, length = 15)
    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    @Basic
    @Column(name = "qteVac", nullable = false)
    public int getQteVac() {
        return qteVac;
    }

    public void setQteVac(int qteVac) {
        this.qteVac = qteVac;
    }

    @Basic
    @Column(name = "qtePoule", nullable = false)
    public int getQtePoule() {
        return qtePoule;
    }

    public void setQtePoule(int qtePoule) {
        this.qtePoule = qtePoule;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "prix", nullable = true, precision = 2)
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
        VaccinEntity that = (VaccinEntity) o;
        return idVac == that.idVac &&
                qteVac == that.qteVac &&
                qtePoule == that.qtePoule &&
                Objects.equals(nomVac, that.nomVac) &&
                Objects.equals(periode, that.periode) &&
                Objects.equals(description, that.description) &&
                Objects.equals(prix, that.prix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVac, nomVac, periode, qteVac, qtePoule, description, prix);
    }
}
