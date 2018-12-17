/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.serviceImpl;

import cm.rst.dao.ProduitCommandeRepository;
import cm.rst.entities.ProduitCommande;
import cm.rst.service.IProduitCommandeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martins
 */
@Service
@Transactional
public class ProduitCommandeServiceImpl implements IProduitCommandeService{

    @Autowired
    private ProduitCommandeRepository produitCommandeRepository;
    
    @Override
    public List<ProduitCommande> listerProduitCommande(int pageNumber, int pageSize) {
        return produitCommandeRepository.findAll(new PageRequest(pageNumber, pageSize)).getContent();
    }

    @Override
    public List<ProduitCommande> listerProduitCommande() {
        return produitCommandeRepository.findAll();
    }

    @Override
    public ProduitCommande voirProduitCommande(Long id) {
        return produitCommandeRepository.findOne(id);
    }

    @Override
    public ProduitCommande creerProduitCommande(ProduitCommande produitCommande) {
        return produitCommandeRepository.save(produitCommande);
    }

    @Override
    public ProduitCommande modifierProduitCommande(ProduitCommande produitCommande) {
        return produitCommandeRepository.save(produitCommande);
    }

    @Override
    public void supprimerProduitCommande(Long id) {
        produitCommandeRepository.delete(id);
    }

    @Override
    public void supprimerProduitCommande(ProduitCommande produitCommande) {
        produitCommandeRepository.delete(produitCommande);
    }
    
}
