package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "maladie", schema = "volailledor", catalog = "")
public class MaladieEntity {
    private int idM;
    private String nomM;
    private String descriptionTraitement;
    private String descriptionMaladie;
    private String duree;

    @Id
    @Column(name = "idM", nullable = false)
    public int getIdM() {
        return idM;
    }

    public void setIdM(int idM) {
        this.idM = idM;
    }

    @Basic
    @Column(name = "nomM", nullable = false, length = 20)
    public String getNomM() {
        return nomM;
    }

    public void setNomM(String nomM) {
        this.nomM = nomM;
    }

    @Basic
    @Column(name = "descriptionTraitement", nullable = true, length = -1)
    public String getDescriptionTraitement() {
        return descriptionTraitement;
    }

    public void setDescriptionTraitement(String descriptionTraitement) {
        this.descriptionTraitement = descriptionTraitement;
    }

    @Basic
    @Column(name = "descriptionMaladie", nullable = true, length = -1)
    public String getDescriptionMaladie() {
        return descriptionMaladie;
    }

    public void setDescriptionMaladie(String descriptionMaladie) {
        this.descriptionMaladie = descriptionMaladie;
    }

    @Basic
    @Column(name = "duree", nullable = true, length = 10)
    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaladieEntity that = (MaladieEntity) o;
        return idM == that.idM &&
                Objects.equals(nomM, that.nomM) &&
                Objects.equals(descriptionTraitement, that.descriptionTraitement) &&
                Objects.equals(descriptionMaladie, that.descriptionMaladie) &&
                Objects.equals(duree, that.duree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idM, nomM, descriptionTraitement, descriptionMaladie, duree);
    }
}
