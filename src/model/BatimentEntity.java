package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "batiment", schema = "volailledor", catalog = "")
public class BatimentEntity {
    private int idBat;
    private BigDecimal surface;
    private String nomBat;

    @Id
    @Column(name = "idBat", nullable = false)
    public int getIdBat() {
        return idBat;
    }

    public void setIdBat(int idBat) {
        this.idBat = idBat;
    }

    @Basic
    @Column(name = "surface", nullable = false, precision = 2)
    public BigDecimal getSurface() {
        return surface;
    }

    public void setSurface(BigDecimal surface) {
        this.surface = surface;
    }

    @Basic
    @Column(name = "nomBat", nullable = false, length = 10)
    public String getNomBat() {
        return nomBat;
    }

    public void setNomBat(String nomBat) {
        this.nomBat = nomBat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatimentEntity that = (BatimentEntity) o;
        return idBat == that.idBat &&
                Objects.equals(surface, that.surface) &&
                Objects.equals(nomBat, that.nomBat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBat, surface, nomBat);
    }
}
