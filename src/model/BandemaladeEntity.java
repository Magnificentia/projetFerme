package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "bandemalade", schema = "volailledor", catalog = "")
public class BandemaladeEntity {
    private int idBandeMalade;
    private Integer qteMalade;
    private Integer qtePrise;
    private Timestamp dateM;
    private Integer totalMort;

    @Id
    @Column(name = "idBandeMalade", nullable = false)
    public int getIdBandeMalade() {
        return idBandeMalade;
    }

    public void setIdBandeMalade(int idBandeMalade) {
        this.idBandeMalade = idBandeMalade;
    }

    @Basic
    @Column(name = "qteMalade", nullable = true)
    public Integer getQteMalade() {
        return qteMalade;
    }

    public void setQteMalade(Integer qteMalade) {
        this.qteMalade = qteMalade;
    }

    @Basic
    @Column(name = "qtePrise", nullable = true)
    public Integer getQtePrise() {
        return qtePrise;
    }

    public void setQtePrise(Integer qtePrise) {
        this.qtePrise = qtePrise;
    }

    @Basic
    @Column(name = "dateM", nullable = true)
    public Timestamp getDateM() {
        return dateM;
    }

    public void setDateM(Timestamp dateM) {
        this.dateM = dateM;
    }

    @Basic
    @Column(name = "totalMort", nullable = true)
    public Integer getTotalMort() {
        return totalMort;
    }

    public void setTotalMort(Integer totalMort) {
        this.totalMort = totalMort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BandemaladeEntity that = (BandemaladeEntity) o;
        return idBandeMalade == that.idBandeMalade &&
                Objects.equals(qteMalade, that.qteMalade) &&
                Objects.equals(qtePrise, that.qtePrise) &&
                Objects.equals(dateM, that.dateM) &&
                Objects.equals(totalMort, that.totalMort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBandeMalade, qteMalade, qtePrise, dateM, totalMort);
    }
}
