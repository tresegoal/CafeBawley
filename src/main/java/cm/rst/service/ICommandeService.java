/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.service;

import cm.rst.entities.Commande;
import java.util.List;

/**
 *
 * @author Martins
 */
public interface ICommandeService {
    public List<Commande> listerCommande(int pageNumber, int pageSize); 
    public List<Commande> listerCommande(); 
    public Commande voirCommande(Long id);
    public Commande creerCommande(Commande commande);
    public Commande modifierCommande(Commande commande);
    public void supprimerCommande(Long id);
    public void supprimerCommande(Commande commande);
}
