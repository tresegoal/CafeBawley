package cm.rst.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Fabrice
 *
 */
@Entity
public class Favoris implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "utilis_id")
    private Utilisateur utilisateur;

    public Favoris() {
        super();
    }

    public Favoris(Produit produit, Utilisateur utilisateur) {
        this.produit = produit;
        this.utilisateur = utilisateur;
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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

}
