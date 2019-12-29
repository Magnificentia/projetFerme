package app.modules.model;
// Generated 29 d�c. 2019 10:37:43 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Employes generated by hbm2java
 */
@Entity
@Table(name="employes"
    ,catalog="volailledor"
    , uniqueConstraints = @UniqueConstraint(columnNames="user") 
)
public class Employes  implements java.io.Serializable {


     private int idEm;
     private String nom;
     private String user;
     private String login;
     private String typeEm;

    public Employes() {
    }

	
    public Employes(int idEm) {
        this.idEm = idEm;
    }
    public Employes(int idEm, String nom, String user, String login, String typeEm) {
       this.idEm = idEm;
       this.nom = nom;
       this.user = user;
       this.login = login;
       this.typeEm = typeEm;
    }
   
     @Id 

    
    @Column(name="idEm", unique=true, nullable=false)
    public int getIdEm() {
        return this.idEm;
    }
    
    public void setIdEm(int idEm) {
        this.idEm = idEm;
    }

    
    @Column(name="nom", length=15)
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    @Column(name="user", unique=true, length=15)
    public String getUser() {
        return this.user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }

    
    @Column(name="login", length=20)
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }

    
    @Column(name="typeEm", length=4)
    public String getTypeEm() {
        return this.typeEm;
    }
    
    public void setTypeEm(String typeEm) {
        this.typeEm = typeEm;
    }




}

