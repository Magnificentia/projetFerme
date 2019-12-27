package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fournisseur", schema = "volailledor", catalog = "")
public class FournisseurEntity {
    private int idFourn;
    private String nomFourn;
    private String adresse;
    private int tel;
    private String email;
    private String siteweb;
    private Integer typeFourn;

    @Id
    @Column(name = "idFourn", nullable = false)
    public int getIdFourn() {
        return idFourn;
    }

    public void setIdFourn(int idFourn) {
        this.idFourn = idFourn;
    }

    @Basic
    @Column(name = "nomFourn", nullable = false, length = 20)
    public String getNomFourn() {
        return nomFourn;
    }

    public void setNomFourn(String nomFourn) {
        this.nomFourn = nomFourn;
    }

    @Basic
    @Column(name = "adresse", nullable = false, length = 20)
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Basic
    @Column(name = "tel", nullable = false)
    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 20)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "siteweb", nullable = true, length = 20)
    public String getSiteweb() {
        return siteweb;
    }

    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
    }

    @Basic
    @Column(name = "typeFourn", nullable = true)
    public Integer getTypeFourn() {
        return typeFourn;
    }

    public void setTypeFourn(Integer typeFourn) {
        this.typeFourn = typeFourn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FournisseurEntity that = (FournisseurEntity) o;
        return idFourn == that.idFourn &&
                tel == that.tel &&
                Objects.equals(nomFourn, that.nomFourn) &&
                Objects.equals(adresse, that.adresse) &&
                Objects.equals(email, that.email) &&
                Objects.equals(siteweb, that.siteweb) &&
                Objects.equals(typeFourn, that.typeFourn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFourn, nomFourn, adresse, tel, email, siteweb, typeFourn);
    }
}
