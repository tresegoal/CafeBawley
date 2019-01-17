package cm.rst.restController;


import cm.rst.entities.Produit;
import cm.rst.restController.utils.HeaderUtil;
import cm.rst.serviceImpl.ProduitServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProduitRESTController {

    private final Logger log = LoggerFactory.getLogger(ProduitRESTController.class);

    @Autowired
    private ProduitServiceImpl produitService;


    /**
     * POST  /produits : Create a new produit.
     *
     * @param produit the produit to create
     * @return the ResponseEntity with status 201 (Created) and with body the new produit, or with status 400 (Bad Request) if the produit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/produits/new",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produit> createProduit(@Valid @RequestBody Produit produit) throws URISyntaxException {
        log.debug("REST request to save Produit : {}", produit);
        if (produit.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("produit", "idexists", "A new produit cannot already have an ID")).body(null);
        }
        Produit result = produitService.creerProduit(produit);
        return ResponseEntity.created(new URI("/api/produits/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("produit", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /produits : Updates an existing produit.
     *
     * @param produit the produit to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated produit,
     * or with status 400 (Bad Request) if the produit is not valid,
     * or with status 500 (Internal Server Error) if the article couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/produits/edit",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produit> updateProduit(@Valid @RequestBody Produit produit) throws URISyntaxException {
        log.debug("REST request to update Produit : {}", produit);
        if (produit.getId() == null) {
            return createProduit(produit);
        }
        Produit result = produitService.modifierProduit(produit);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("produit", produit.getId().toString()))
                .body(result);
    }

    /**
     * GET  /produits : get all the produits.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of articles in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/produits",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Produit>> getAllProduits()
            throws URISyntaxException {
        log.debug("REST request to get a list of Produits");
        List<Produit> produits = produitService.listerProduit();
        HttpHeaders headers = HeaderUtil.getListAlert("That is the Set of produits after /api/produits ", String.valueOf(produits.size()));
        return new ResponseEntity<>(produits, headers, HttpStatus.OK);
    }

    /**
     * GET  /produits : get all the produits.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of articles in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/produits/categories/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Produit>> getAllProduitsParCategorie(@PathVariable Long id)
            throws URISyntaxException {
        log.debug("REST request to get a list of Produits");
        List<Produit> produits = produitService.listeProduitParCat(id);
        HttpHeaders headers = HeaderUtil.getListAlert("That is the Set of produits after /api/produits ", String.valueOf(produits.size()));
        return new ResponseEntity<>(produits, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/produitsPromotion",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Produit>> getAllPromotionProduits()
            throws URISyntaxException {
        log.debug("REST request to get a list of promotion Produits");
        List<Produit> produits = produitService.listerProduitPromotion();
        HttpHeaders headers = HeaderUtil.getListAlert("That is the Set of produits after /api/produits ", String.valueOf(produits.size()));
        return new ResponseEntity<>(produits, headers, HttpStatus.OK);
    }

    /**
     * GET  /produits/:id : get the "id" produit.
     *
     * @param id the id of the produit to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the produit, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/produits/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produit> getProduit(@PathVariable Long id) {
        log.debug("REST request to get Produit : {}", id);
        Produit produit = produitService.voirProduit(id);
        return Optional.ofNullable(produit)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /produits/:id : delete the "id" produits.
     *
     * @param id the id of the produit to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/produits/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        log.debug("REST request to delete Produit : {}", id);
        produitService.supprimerProduit(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("produit", id.toString())).build();
    }
}
