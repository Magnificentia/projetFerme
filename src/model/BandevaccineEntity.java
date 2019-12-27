package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "bandevaccine", schema = "volailledor", catalog = "")
public class BandevaccineEntity {
    private int idBandeVaccine;
    private Timestamp dateVac;

    @Id
    @Column(name = "idBandeVaccine", nullable = false)
    public int getIdBandeVaccine() {
        return idBandeVaccine;
    }

    public void setIdBandeVaccine(int idBandeVaccine) {
        this.idBandeVaccine = idBandeVaccine;
    }

    @Basic
    @Column(name = "dateVac", nullable = true)
    public Timestamp getDateVac() {
        return dateVac;
    }

    public void setDateVac(Timestamp dateVac) {
        this.dateVac = dateVac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BandevaccineEntity that = (BandevaccineEntity) o;
        return idBandeVaccine == that.idBandeVaccine &&
                Objects.equals(dateVac, that.dateVac);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBandeVaccine, dateVac);
    }
}
