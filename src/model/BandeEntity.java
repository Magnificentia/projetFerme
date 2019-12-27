package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "bande", schema = "volailledor", catalog = "")
public class BandeEntity {
    private int idBande;
    private int qte;
    private int age;
    private BigDecimal prixAchat;
    private BigDecimal prixVente;
    private Timestamp dateDemarrage;

    @Id
    @Column(name = "idBande", nullable = false)
    public int getIdBande() {
        return idBande;
    }

    public void setIdBande(int idBande) {
        this.idBande = idBande;
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
    @Column(name = "age", nullable = false)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Basic
    @Column(name = "prix_achat", nullable = true, precision = 2)
    public BigDecimal getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(BigDecimal prixAchat) {
        this.prixAchat = prixAchat;
    }

    @Basic
    @Column(name = "prix_vente", nullable = true, precision = 2)
    public BigDecimal getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(BigDecimal prixVente) {
        this.prixVente = prixVente;
    }

    @Basic
    @Column(name = "dateDemarrage", nullable = true)
    public Timestamp getDateDemarrage() {
        return dateDemarrage;
    }

    public void setDateDemarrage(Timestamp dateDemarrage) {
        this.dateDemarrage = dateDemarrage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BandeEntity that = (BandeEntity) o;
        return idBande == that.idBande &&
                qte == that.qte &&
                age == that.age &&
                Objects.equals(prixAchat, that.prixAchat) &&
                Objects.equals(prixVente, that.prixVente) &&
                Objects.equals(dateDemarrage, that.dateDemarrage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBande, qte, age, prixAchat, prixVente, dateDemarrage);
    }
}
