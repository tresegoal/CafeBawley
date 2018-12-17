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
    private List<ProduitCommande> ProduitCommandes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorie_id", nullable = false)
    @Valid
    private Categorie categorie;
    
    @OneToOne(mappedBy = "produit")
    private Image image;
    
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

    public Produit(String designation, String description, Double prixUnitHT, int quantite, int tva, Categorie categorie) {
        this.designation = designation;
        this.description = description;
        this.prixUnitHT = prixUnitHT;
        this.quantite = quantite;
        this.tva = tva;
        this.categorie = categorie;
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
