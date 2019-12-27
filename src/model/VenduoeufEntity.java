package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "venduoeuf", schema = "volailledor", catalog = "")
public class VenduoeufEntity {
    private int idVenduOeuf;
    private Timestamp dateVente;
    private BigDecimal totalPrix;
    private int qte;

    @Id
    @Column(name = "idVenduOeuf", nullable = false)
    public int getIdVenduOeuf() {
        return idVenduOeuf;
    }

    public void setIdVenduOeuf(int idVenduOeuf) {
        this.idVenduOeuf = idVenduOeuf;
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
    @Column(name = "total_prix", nullable = true, precision = 2)
    public BigDecimal getTotalPrix() {
        return totalPrix;
    }

    public void setTotalPrix(BigDecimal totalPrix) {
        this.totalPrix = totalPrix;
    }

    @Basic
    @Column(name = "qte", nullable = false)
    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VenduoeufEntity that = (VenduoeufEntity) o;
        return idVenduOeuf == that.idVenduOeuf &&
                qte == that.qte &&
                Objects.equals(dateVente, that.dateVente) &&
                Objects.equals(totalPrix, that.totalPrix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVenduOeuf, dateVente, totalPrix, qte);
    }
}
