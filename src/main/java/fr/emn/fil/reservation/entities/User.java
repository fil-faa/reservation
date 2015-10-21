package fr.emn.fil.reservation.entities;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "user.findAll", query = "SELECT u FROM User u"),
        @NamedQuery(name = "user.byMail", query = "SELECT u FROM User u WHERE u.mail = :mail")
})
public class User {
    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private String password;
    private String telephone;
    private boolean isadmin;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOM")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "PRENOM")
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Basic
    @Column(name = "MAIL")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "TELEPHONE")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(boolean isadmin) {
        this.isadmin = isadmin;
    }

    public User(String nom, String prenom, String mail, String password, String telephone, boolean isadmin) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.password = password;
        this.telephone = telephone;
        this.isadmin = isadmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (nom != null ? !nom.equals(user.nom) : user.nom != null) return false;
        if (prenom != null ? !prenom.equals(user.prenom) : user.prenom != null) return false;
        if (mail != null ? !mail.equals(user.mail) : user.mail != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (telephone != null ? !telephone.equals(user.telephone) : user.telephone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        return result;
    }

    public static User getUser(int id)
    {
        EntityManager entityManager = Persistence.createEntityManagerFactory("persistenceUnit").createEntityManager();
        entityManager.getTransaction().begin();
        User foundUser = entityManager.find(User.class, id);
        entityManager.close();
        return foundUser;
    }
}
