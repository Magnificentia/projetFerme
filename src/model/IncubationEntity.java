package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "incubation", schema = "volailledor", catalog = "")
public class IncubationEntity {
    private int idInc;
    private Timestamp dateInc;
    private Integer produirePoussinId;

    @Id
    @Column(name = "idInc", nullable = false)
    public int getIdInc() {
        return idInc;
    }

    public void setIdInc(int idInc) {
        this.idInc = idInc;
    }

    @Basic
    @Column(name = "dateInc", nullable = true)
    public Timestamp getDateInc() {
        return dateInc;
    }

    public void setDateInc(Timestamp dateInc) {
        this.dateInc = dateInc;
    }

    @Basic
    @Column(name = "ProduirePoussin_id", nullable = true)
    public Integer getProduirePoussinId() {
        return produirePoussinId;
    }

    public void setProduirePoussinId(Integer produirePoussinId) {
        this.produirePoussinId = produirePoussinId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncubationEntity that = (IncubationEntity) o;
        return idInc == that.idInc &&
                Objects.equals(dateInc, that.dateInc) &&
                Objects.equals(produirePoussinId, that.produirePoussinId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInc, dateInc, produirePoussinId);
    }
}
