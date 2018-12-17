/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.service;

import cm.rst.entities.ProduitCommande;
import java.util.List;

/**
 *
 * @author Martins
 */
public interface IProduitCommandeService {
    public List<ProduitCommande> listerProduitCommande(int pageNumber, int pageSize); 
    public List<ProduitCommande> listerProduitCommande(); 
    public ProduitCommande voirProduitCommande(Long id);
    public ProduitCommande creerProduitCommande(ProduitCommande produitCommande);
    public ProduitCommande modifierProduitCommande(ProduitCommande produitCommande);
    public void supprimerProduitCommande(Long id);
    public void supprimerProduitCommande(ProduitCommande produitCommande);
}
