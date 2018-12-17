/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.serviceImpl;

import cm.rst.dao.CommandeRepository;
import cm.rst.entities.Commande;
import cm.rst.service.ICommandeService;
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
public class CommandeServiceImpl implements ICommandeService{
    
    @Autowired
    private CommandeRepository commandeRepository;

    @Override
    public List<Commande> listerCommande(int pageNumber, int pageSize) {
        return commandeRepository.findAll(new PageRequest(pageNumber, pageSize)).getContent();
    }

    @Override
    public List<Commande> listerCommande() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande voirCommande(Long id) {
        return commandeRepository.findOne(id);
    }

    @Override
    public Commande creerCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public Commande modifierCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public void supprimerCommande(Long id) {
        commandeRepository.delete(id);
    }

    @Override
    public void supprimerCommande(Commande commande) {
        commandeRepository.delete(commande);
    }
    
}
