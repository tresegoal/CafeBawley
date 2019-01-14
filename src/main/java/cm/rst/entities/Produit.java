/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.codehaus.jackson.annotate.JsonIgnore;
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
@JsonIgnoreProperties({"produitCommandes","dateCreation","dateModification"})
public class Produit implements Serializable{
    @Id @GeneratedValue
    private Long id;
    @NotBlank
    private String designation;
    @NotBlank
    private String description;
    @NotNull
    private Double prixUnitHT;
    @NotNull
    private int quantite;
    @NotNull
    private int tva;
    
    private boolean active= false;

    @OneToMany(mappedBy = "produit")
    //@JsonIgnoreProperties({"produitCommandes"})
    private List<ProduitCommande> ProduitCommandes;

    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    @Valid
    @JsonIgnore
    private Categorie categorie;

    @OneToOne(mappedBy = "produit")
    //@JsonIgnore
    private Image image;

    private boolean promotion;
    
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

    public Produit(String designation, Date dateModification, Date dateCreation,
                   boolean promotion, Image image, Categorie categorie, List<ProduitCommande> produitCommandes,
                   boolean active, int tva, int quantite, Double prixUnitHT, String description) {
        this.designation = designation;
        this.dateModification = dateModification;
        this.dateCreation = dateCreation;
        this.promotion = promotion;
        this.image = image;
        this.categorie = categorie;
        ProduitCommandes = produitCommandes;
        this.active = active;
        this.tva = tva;
        this.quantite = quantite;
        this.prixUnitHT = prixUnitHT;
        this.description = description;
    }

    public Produit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrixUnitHT() {
        return prixUnitHT;
    }

    public void setPrixUnitHT(Double prixUnitHT) {
        this.prixUnitHT = prixUnitHT;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getTva() {
        return tva;
    }

    public void setTva(int tva) {
        this.tva = tva;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<ProduitCommande> getProduitCommandes() {
        return ProduitCommandes;
    }

    public void setProduitCommandes(List<ProduitCommande> produitCommandes) {
        ProduitCommandes = produitCommandes;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }



}
