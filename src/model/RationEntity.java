package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "ration", schema = "volailledor", catalog = "")
public class RationEntity {
    private int idRation;
    private Timestamp dateRation;
    private BigDecimal qte;
    private BigDecimal eau;

    @Id
    @Column(name = "idRation", nullable = false)
    public int getIdRation() {
        return idRation;
    }

    public void setIdRation(int idRation) {
        this.idRation = idRation;
    }

    @Basic
    @Column(name = "dateRation", nullable = false)
    public Timestamp getDateRation() {
        return dateRation;
    }

    public void setDateRation(Timestamp dateRation) {
        this.dateRation = dateRation;
    }

    @Basic
    @Column(name = "qte", nullable = false, precision = 2)
    public BigDecimal getQte() {
        return qte;
    }

    public void setQte(BigDecimal qte) {
        this.qte = qte;
    }

    @Basic
    @Column(name = "eau", nullable = false, precision = 2)
    public BigDecimal getEau() {
        return eau;
    }

    public void setEau(BigDecimal eau) {
        this.eau = eau;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RationEntity that = (RationEntity) o;
        return idRation == that.idRation &&
                Objects.equals(dateRation, that.dateRation) &&
                Objects.equals(qte, that.qte) &&
                Objects.equals(eau, that.eau);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRation, dateRation, qte, eau);
    }
}
