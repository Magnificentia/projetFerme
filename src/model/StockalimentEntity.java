package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "stockaliment", schema = "volailledor", catalog = "")
public class StockalimentEntity {
    private int idStock;
    private int qte;
    private Timestamp dateArrivage;

    @Id
    @Column(name = "idStock", nullable = false)
    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
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
    @Column(name = "dateArrivage", nullable = true)
    public Timestamp getDateArrivage() {
        return dateArrivage;
    }

    public void setDateArrivage(Timestamp dateArrivage) {
        this.dateArrivage = dateArrivage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockalimentEntity that = (StockalimentEntity) o;
        return idStock == that.idStock &&
                qte == that.qte &&
                Objects.equals(dateArrivage, that.dateArrivage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStock, qte, dateArrivage);
    }
}
