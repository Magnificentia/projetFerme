package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "client", schema = "volailledor", catalog = "")
public class ClientEntity {
    private int idClient;
    private String adresse;
    private Integer tel;

    @Id
    @Column(name = "idClient", nullable = false)
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Basic
    @Column(name = "adresse", nullable = true, length = 15)
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Basic
    @Column(name = "tel", nullable = true)
    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return idClient == that.idClient &&
                Objects.equals(adresse, that.adresse) &&
                Objects.equals(tel, that.tel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, adresse, tel);
    }
}
