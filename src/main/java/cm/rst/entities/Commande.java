/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
public class Commande implements Serializable{
    @Id @GeneratedValue
    private long id;
    private String statut;
    
    @OneToMany(mappedBy = "commande")
    private List<ProduitCommande> ProduitCommandes;
    
   @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;
    
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

    public Commande() {
    }

    public Commande(String statut, List<ProduitCommande> ProduitCommandes, Date dateCreation, Date dateModification) {
        this.statut = statut;
        this.ProduitCommandes = ProduitCommandes;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public List<ProduitCommande> getProduitCommandes() {
        return ProduitCommandes;
    }

    public void setProduitCommandes(List<ProduitCommande> ProduitCommandes) {
        this.ProduitCommandes = ProduitCommandes;
    }
    
    
}
