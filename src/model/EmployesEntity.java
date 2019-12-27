package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employes", schema = "volailledor", catalog = "")
public class EmployesEntity {
    private int idEm;
    private String nom;
    private String user;
    private String login;
    private String typeEm;

    @Id
    @Column(name = "idEm", nullable = false)
    public int getIdEm() {
        return idEm;
    }

    public void setIdEm(int idEm) {
        this.idEm = idEm;
    }

    @Basic
    @Column(name = "nom", nullable = true, length = 15)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "user", nullable = true, length = 15)
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Basic
    @Column(name = "login", nullable = true, length = 20)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "typeEm", nullable = true, length = 4)
    public String getTypeEm() {
        return typeEm;
    }

    public void setTypeEm(String typeEm) {
        this.typeEm = typeEm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployesEntity that = (EmployesEntity) o;
        return idEm == that.idEm &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(user, that.user) &&
                Objects.equals(login, that.login) &&
                Objects.equals(typeEm, that.typeEm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEm, nom, user, login, typeEm);
    }
}
