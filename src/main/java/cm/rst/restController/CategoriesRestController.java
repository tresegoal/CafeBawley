/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.restController;

import cm.rst.entities.Categorie;
import cm.rst.serviceImpl.CategorieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Martins
 */
@RestController
@RequestMapping(value = "/api")
public class CategoriesRestController {
    @Autowired
    private CategorieServiceImpl categorieService;
    
    @RequestMapping(value = "/cat",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Categorie>> list() {
         List<Categorie> clients = categorieService.listerCategorie();
         return new ResponseEntity<>(clients, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/supprimerclient/{id}",method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable("id") long id) {
         categorieService.supprimerCategorie(id);
         return new ResponseEntity(HttpStatus.OK);
    }
}
 