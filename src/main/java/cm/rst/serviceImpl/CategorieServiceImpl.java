/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.serviceImpl;

import cm.rst.dao.CategorieRepository;
import cm.rst.entities.Categorie;
import cm.rst.service.ICategorieService;
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
public class CategorieServiceImpl implements ICategorieService{

    @Autowired
    private CategorieRepository categorieRepository;
    
    @Override
    public List<Categorie> listerCategorie(int pageNumber, int pageSize) {
        return categorieRepository.findAll(new PageRequest(pageNumber, pageSize)).getContent();
    }

    @Override
    public List<Categorie> listerCategorie() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie voirCategorie(Long id) {
        return categorieRepository.findOne(id);
    }

    @Override
    public Categorie creerCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie modifierCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public void supprimerCategorie(Long id) {
        categorieRepository.delete(id);
    }

    @Override
    public void supprimerCategorie(Categorie categorie) {
         categorieRepository.delete(categorie);
    }
    
}
