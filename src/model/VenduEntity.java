package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "vendu", schema = "volailledor", catalog = "")
public class VenduEntity {
    private int idVente;
    private Timestamp dateVente;
    private BigDecimal totalPrix;
    private Integer qte;

    @Id
    @Column(name = "idVente", nullable = false)
    public int getIdVente() {
        return idVente;
    }

    public void setIdVente(int idVente) {
        this.idVente = idVente;
    }

    @Basic
    @Column(name = "dateVente", nullable = true)
    public Timestamp getDateVente() {
        return dateVente;
    }

    public void setDateVente(Timestamp dateVente) {
        this.dateVente = dateVente;
    }

    @Basic
    @Column(name = "total_prix", nullable = false, precision = 2)
    public BigDecimal getTotalPrix() {
        return totalPrix;
    }

    public void setTotalPrix(BigDecimal totalPrix) {
        this.totalPrix = totalPrix;
    }

    @Basic
    @Column(name = "qte", nullable = true)
    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VenduEntity that = (VenduEntity) o;
        return idVente == that.idVente &&
                Objects.equals(dateVente, that.dateVente) &&
                Objects.equals(totalPrix, that.totalPrix) &&
                Objects.equals(qte, that.qte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVente, dateVente, totalPrix, qte);
    }
}
