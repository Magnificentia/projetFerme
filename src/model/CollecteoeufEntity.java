package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "collecteoeuf", schema = "volailledor", catalog = "")
public class CollecteoeufEntity {
    private int idCollect;
    private int qte;
    private Timestamp dateCollect;
    private Integer incubation;
    private BigDecimal prixAlveole;
    private Integer qteCasse;

    @Id
    @Column(name = "idCollect", nullable = false)
    public int getIdCollect() {
        return idCollect;
    }

    public void setIdCollect(int idCollect) {
        this.idCollect = idCollect;
    }

    @Basic
    @Column(name = "qte", nullable = false)
    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    @Basic
    @Column(name = "dateCollect", nullable = true)
    public Timestamp getDateCollect() {
        return dateCollect;
    }

    public void setDateCollect(Timestamp dateCollect) {
        this.dateCollect = dateCollect;
    }

    @Basic
    @Column(name = "incubation", nullable = true)
    public Integer getIncubation() {
        return incubation;
    }

    public void setIncubation(Integer incubation) {
        this.incubation = incubation;
    }

    @Basic
    @Column(name = "prix_alveole", nullable = true, precision = 2)
    public BigDecimal getPrixAlveole() {
        return prixAlveole;
    }

    public void setPrixAlveole(BigDecimal prixAlveole) {
        this.prixAlveole = prixAlveole;
    }

    @Basic
    @Column(name = "qteCasse", nullable = true)
    public Integer getQteCasse() {
        return qteCasse;
    }

    public void setQteCasse(Integer qteCasse) {
        this.qteCasse = qteCasse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollecteoeufEntity that = (CollecteoeufEntity) o;
        return idCollect == that.idCollect &&
                qte == that.qte &&
                Objects.equals(dateCollect, that.dateCollect) &&
                Objects.equals(incubation, that.incubation) &&
                Objects.equals(prixAlveole, that.prixAlveole) &&
                Objects.equals(qteCasse, that.qteCasse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCollect, qte, dateCollect, incubation, prixAlveole, qteCasse);
    }
}
