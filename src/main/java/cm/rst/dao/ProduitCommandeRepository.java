/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.dao;

import cm.rst.entities.ProduitCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martins
 */
@Repository
public interface ProduitCommandeRepository extends JpaRepository<ProduitCommande, Long>{
    
}
