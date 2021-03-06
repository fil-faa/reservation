package fr.emn.fil.reservation.model.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores the user of booking web app.
 * Different types of users exists : the admin can administrate the resources, while
 * the average user can book a specific resource during a given period
 */
@Entity
@Table(name = "USER")
@NamedQueries({
        @NamedQuery(name = "user.findAll", query = "SELECT u FROM User u"),
        @NamedQuery(name = "user.byMail", query = "SELECT u FROM User u WHERE u.mail = :mail")
})
public class User {


    /**----------------------------------------------
     *               ENTITY FIELDS
     *---------------------------------------------*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)
    @Column(name = "FIRST_NAME")
    private @NotNull String firstName;

    @Basic(optional = false)
    @Column(name = "LAST_NAME")
    private @NotNull String lastName;

    @Basic(optional = false)
    @Column(name = "MAIL", unique = true)
    private @NotNull String mail;

    /**
     * Password, hashed in SHA-256
     */
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private @NotNull String password;

    @Basic
    @Column(name = "TELEPHONE")
    private String telephone;

    @Basic(optional = false)
    @Column(name = "ADMIN")
    private boolean admin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reservation> reservations;



    /**----------------------------------------------
     *                CONSTRUCTORS
     *---------------------------------------------*/

    public User() {
    }

    public User(String firstName, String lastName, String mail, String password, String phone, boolean admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.telephone = phone;
        this.admin = admin;
        this.reservations = new ArrayList<>();
    }

    public User(Long id, String firstName, String lastName, String mail, String password, String phone, boolean admin) {
        this(firstName, lastName, mail, password, phone, admin);
        this.id = id;
    }



    /**----------------------------------------------
     *             GETTERS AND SETTERS
     *---------------------------------------------*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }



    /**----------------------------------------------
     *              EQUALS AND HASHCODE
     *---------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (admin != user.admin) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (mail != null ? !mail.equals(user.mail) : user.mail != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (telephone != null ? !telephone.equals(user.telephone) : user.telephone != null) return false;
        return !(reservations != null ? !reservations.equals(user.reservations) : user.reservations != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (admin ? 1 : 0);
        result = 31 * result + (reservations != null ? reservations.hashCode() : 0);
        return result;
    }
}
