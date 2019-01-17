/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.serviceImpl;

import cm.rst.dao.ProduitRepository;
import cm.rst.entities.Produit;
import cm.rst.service.IProduitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martins
 */
@Service
@Transactional
public class ProduitServiceImpl implements IProduitService{
    
    @Autowired
    private ProduitRepository produitRepository;
    
    @Override
    public Page<Produit> listerProduit(int pageNumber, int pageSize) {
        return produitRepository.findAll(new PageRequest(pageNumber, pageSize));
    }

    @Override
    public List<Produit> listerProduit() {
        return produitRepository.findAll();
    }

    @Override
    public List<Produit> listerProduitPromotion() {
        return produitRepository.findByPromotion();
    }

    @Override
    public Produit voirProduit(Long id) {
        return produitRepository.findOne(id);
    }

    @Override
    public Produit creerProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public Produit modifierProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public void supprimerProduit(Long id) {
        produitRepository.delete(id);
    }

    @Override
    public void supprimerProduit(Produit produit) {
        produitRepository.delete(produit);
    }

    @Override
    public Page<Produit> listeReduiteProduit() {
        return produitRepository.findLimitProduct(new PageRequest(0,8));
    }

    @Override
    public List<Produit> listeProduitParMc(String key) {
        return produitRepository.findByKeyword(key);
    }

    @Override
    public List<Produit> listeProduitParCat(Long catId) {
        return produitRepository.findByCategorie(catId);
    }

    @Override
    public Page<Produit> listeProduitParPrix(double d, double f,int pageNumber, int pageSize) {
        return produitRepository.findByPrice(d,f,new PageRequest(pageNumber, pageSize));    }
}
