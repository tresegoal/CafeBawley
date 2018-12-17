/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Martins
 */
@Entity
public class ProduitCommande implements Serializable{
    @Id @GeneratedValue
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "produit_id", referencedColumnName = "id")
    private Produit produit;
 
    @ManyToOne
    @JoinColumn(name = "commande_id", referencedColumnName = "id")
    private Commande commande;
    
    private int quantiteCmd;
    private Double prixCmd;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateCmd;
    
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

    public ProduitCommande() {
    }

    public ProduitCommande(Produit produit, int quantiteCmd) {
        this.produit = produit;
        this.quantiteCmd = quantiteCmd;
    }

    public ProduitCommande(Produit produit, Commande commande, int quantiteCmd, Double prixCmd, Date dateCmd, Date dateCreation, Date dateModification) {
        this.produit = produit;
        this.commande = commande;
        this.quantiteCmd = quantiteCmd;
        this.prixCmd = prixCmd;
        this.dateCmd = dateCmd;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public int getQuantiteCmd() {
        return quantiteCmd;
    }

    public void setQuantiteCmd(int quantiteCmd) {
        this.quantiteCmd = quantiteCmd;
    }

    public Double getPrixCmd() {
        return prixCmd;
    }

    public void setPrixCmd(Double prixCmd) {
        this.prixCmd = prixCmd;
    }

    public Date getDateCmd() {
        return dateCmd;
    }

    public void setDateCmd(Date dateCmd) {
        this.dateCmd = dateCmd;
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
