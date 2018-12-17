package cm.rst.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Fabrice
 * Entité utilisateur (admin, publisher, client etc.)
 * en fonction du role qui lui sera attribué
 */
@Entity
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    /**
     * Nom de l'utilisateur
     */
   // @NotBlank(message = "Le nom ne peut pas être vide")
   // @Size(min = 3, max = 30, message = "Le nom doit comporté au moins 3 caractères")
    private String nom;
    /**
     * Prenom de l'utilisateur
     */
   // @Size(min = 3, max = 30, message = "Le nom doit comporté au moins 3 caractères")
    private String prenom;
    /**
     * Mot de passe de l'utilisateur
     */
    //@NotBlank(message = "Le mot de passe ne peut pas être vide")
    //@NotNull(message = "Le mot de passe est obligatoire")
    private String password;

    @Transient
    private String passwordConfirm;

    /**
     * Email de l'utilisateur
     */
    //@NotNull(message = "L'email ne peut pas être vide")
    @Email(message = "Le format de l'email est invalide")
    //@NotBlank(message = "Le champ email ne peut pas être vide")
    @Size(min = 3, max = 30)
    @Column(unique = true)
    private String email;
    /**
     * Numéro de téléphone de l'utilisateur
     */
    //@Column(unique=true)
    private String telephone;
    /**
     * Date de création de l'utilisateur.
     * Elle correspondra à la date courante au
     * moment de la création de l'utilisateur
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    //@Column(nullable=false, updatable=false)
    private Date dateDeCreation;
    /**
     * Date de modification de l'utilisateur.
     * Elle sera mis à jour à chaque modification
     * d'une information sur l'utilisateur
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date dateDeModification;
    /**
     * Correspond à l'action ou pas de l'utilsateur
     */
    private boolean active = false;

    private String provider;
    private String image;

    /**
     * Liste des adresses de l'utilsateur.
     * Chaque adresse doit être unique.
     * A la suppression de l'utilisateur, toutes ses adresses doivent
     * également êtres supprimées
     */
    @OneToMany(mappedBy = "user")
    private List<Adresse> adresses = new ArrayList<>();

    /**
     * La liste des rôles de l'utilisateur.
     * Chaque rôle qui lui est attribué doit être unique
     * A la suppression de l'utilisateur, ses rôles doivent êtres supprimés également.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur")
    private List<Commande> commandes = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur")
    private List<Favoris> favoris = new ArrayList<>();

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String password, String email, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
    }

    public Utilisateur(String nom, String prenom, String password, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
    }

    public Utilisateur(String nom, String email, String password) {
        this.nom = nom;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(Date dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public Date getDateDeModification() {
        return dateDeModification;
    }

    public void setDateDeModification(Date dateDeModification) {
        this.dateDeModification = dateDeModification;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Adresse> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Adresse> adresses) {
        this.adresses = adresses;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public List<Favoris> getFavoris() {
        return favoris;
    }

    public void setFavoris(List<Favoris> favoris) {
        this.favoris = favoris;
    }
}