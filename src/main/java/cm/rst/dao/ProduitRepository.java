/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.dao;

import cm.rst.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Martins
 */
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long>{

    @Query("select p from Produit p left join p.categorie c where p.active = 'true' and c.active='true' order by p.dateModification desc")
    Page<Produit> findLimitProduct(Pageable pageable);

    @Query(value = "select p from Produit p left join p.categorie c where  p.designation  LIKE CONCAT('%',:key,'%') and p.active = 'true' and c.active='true'" )
    List<Produit> findByKeyword(@Param("key") String key);

    @Query("select p from Produit p left join p.categorie c where p.active = 'true' and  p.prixUnitHT between :prixDebut and :prixFin")
    Page<Produit> findByPrice(@Param("prixDebut") double prixDebut, @Param("prixFin") double prixFin,Pageable pageable);

    @Query("select p from Produit p left join p.categorie c where p.categorie =?1 and p.active = 'true'")
    Page<Produit> findByCategorie(Pageable pageable, int catId);

    @Query("select p from Produit p left join p.categorie c where p.promotion = 'true' and p.active = 'true' and c.active='true' order by p.dateModification desc")
    List<Produit> findByPromotion();

}
