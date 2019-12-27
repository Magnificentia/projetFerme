package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "produirepoussin", schema = "volailledor", catalog = "")
public class ProduirepoussinEntity {
    private int idProduirePoussin;
    private int qte;
    private BigDecimal taux;

    @Id
    @Column(name = "idProduirePoussin", nullable = false)
    public int getIdProduirePoussin() {
        return idProduirePoussin;
    }

    public void setIdProduirePoussin(int idProduirePoussin) {
        this.idProduirePoussin = idProduirePoussin;
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
    @Column(name = "taux", nullable = true, precision = 2)
    public BigDecimal getTaux() {
        return taux;
    }

    public void setTaux(BigDecimal taux) {
        this.taux = taux;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduirepoussinEntity that = (ProduirepoussinEntity) o;
        return idProduirePoussin == that.idProduirePoussin &&
                qte == that.qte &&
                Objects.equals(taux, that.taux);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduirePoussin, qte, taux);
    }
}
