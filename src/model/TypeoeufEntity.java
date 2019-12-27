package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "typeoeuf", schema = "volailledor", catalog = "")
public class TypeoeufEntity {
    private int idTypeOeuf;
    private String nomTf;
    private BigDecimal prixAlveole;

    @Id
    @Column(name = "idTypeOeuf", nullable = false)
    public int getIdTypeOeuf() {
        return idTypeOeuf;
    }

    public void setIdTypeOeuf(int idTypeOeuf) {
        this.idTypeOeuf = idTypeOeuf;
    }

    @Basic
    @Column(name = "nomTf", nullable = true, length = 10)
    public String getNomTf() {
        return nomTf;
    }

    public void setNomTf(String nomTf) {
        this.nomTf = nomTf;
    }

    @Basic
    @Column(name = "prix_alveole", nullable = true, precision = 2)
    public BigDecimal getPrixAlveole() {
        return prixAlveole;
    }

    public void setPrixAlveole(BigDecimal prixAlveole) {
        this.prixAlveole = prixAlveole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeoeufEntity that = (TypeoeufEntity) o;
        return idTypeOeuf == that.idTypeOeuf &&
                Objects.equals(nomTf, that.nomTf) &&
                Objects.equals(prixAlveole, that.prixAlveole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTypeOeuf, nomTf, prixAlveole);
    }
}
