/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.service;

import cm.rst.entities.Categorie;
import java.util.List;

/**
 *
 * @author Martins
 */
public interface ICategorieService {
    public List<Categorie> listerCategorie(int pageNumber, int pageSize); 
    public List<Categorie> listerCategorie(); 
    public Categorie voirCategorie(Long id);
    public Categorie creerCategorie(Categorie categorie);
    public Categorie modifierCategorie(Categorie categorie);
    public void supprimerCategorie(Long id);
    public void supprimerCategorie(Categorie categorie);
}
