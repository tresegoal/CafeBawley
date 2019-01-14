/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Martins
 */
@Entity
//@JsonIgnoreProperties({"produits"})
@JsonIgnoreProperties({"dateCreation","dateModification"})
public class Categorie implements Serializable{
    @Id @GeneratedValue
    private Long id;
    
    @NotBlank @Size(min = 3, max = 15) 
    @Column(name = "designation", length = 30, nullable = false, unique = true)
    private String designation;

    @OneToMany(mappedBy = "categorie")
    @JsonIgnore
    private List<Produit> Produits;
    
   // @Column(columnDefinition="tinyint(1) default 0")
    private boolean active = false;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date dateCreation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column( nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @UpdateTimestamp
    private Date dateModification;


    private String image;

    public Categorie() {
        super();
    }

    public Categorie(String designation,Date dateCreation, Date dateModification, String image) {
        super();
        this.designation = designation;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public Boolean getActive() {
        return active;
    }
    

    public void setId(Long id) {
        this.id = id;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Produit> getProduits() {
        return Produits;
    }

    public void setProduits(List<Produit> Produits) {
        this.Produits = Produits;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                ", Produits=" + Produits +
                ", active=" + active +
                ", dateCreation=" + dateCreation +
                ", dateModification=" + dateModification +
                ", image='" + image + '\'' +
                '}';
    }
}
