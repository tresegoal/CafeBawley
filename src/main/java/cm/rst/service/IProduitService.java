/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.service;

import cm.rst.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 * @author Martins
 */
public interface IProduitService {
    public Page<Produit> listerProduit(int pageNumber, int pageSize);
    public List<Produit> listerProduit();
    public List<Produit> listerProduitPromotion();
    public Produit voirProduit(Long id);
    public Produit creerProduit(Produit produit);
    public Produit modifierProduit(Produit produit);
    public void supprimerProduit(Long id);
    public void supprimerProduit(Produit produit);
    public Page<Produit> listeReduiteProduit();
    public List<Produit> listeProduitParMc(String key);
    public List<Produit> listeProduitParCat(Long catId);
    public Page<Produit> listeProduitParPrix(double d, double f, int pageNumber, int pageSize);

}
