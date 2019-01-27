package cm.rst.restController;

/**
 * Created by Martins on 08/01/2019.
 */

import cm.rst.entities.Categorie;
import cm.rst.restController.utils.HeaderUtil;
import cm.rst.restController.utils.TokenAuthenticationFilter;
import cm.rst.restController.utils.TokenProvider;
import cm.rst.serviceImpl.CategorieServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class CategorieRestController {
    private final Logger log = LoggerFactory.getLogger(CategorieRestController.class);

    @Autowired
    private CategorieServiceImpl categorieService;
    @Autowired
    private TokenProvider tokenProvider;

    private TokenAuthenticationFilter tokenAuthenticationFilter = new TokenAuthenticationFilter();


    /**
     * POST  /categories : Create a new categorie.
     *
     * @param categorie the categorie to create
     * @return the ResponseEntity with status 201 (Created) and with body the new categorie, or with status 400 (Bad Request) if the categorie has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/categorie/new",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categorie> createCategorie(@Valid @RequestBody Categorie categorie) throws URISyntaxException {

        log.debug("REST request to save Categorie : {}", categorie);
        if (categorie.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("categorie", "idexists", "A new categorie cannot already have an ID")).body(null);
        }
        Categorie result = categorieService.creerCategorie(categorie);
        return ResponseEntity.created(new URI("/api/categories/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("categorie", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /categories : Updates an existing categorie.
     *
     * @param categorie the categorie to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated categorie,
     * or with status 400 (Bad Request) if the categorie is not valid,
     * or with status 500 (Internal Server Error) if the article couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/categories/edit",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categorie> updateCategorie(@Valid @RequestBody Categorie categorie) throws URISyntaxException {
        log.debug("REST request to update Categorie : {}", categorie);
        if (categorie.getId() == null) {
            return  createCategorie(categorie);
        }
        Categorie result = categorieService.modifierCategorie(categorie);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("categorie", categorie.getId().toString()))
                .body(result);
    }

    /**
     * GET  /categories : get all the categories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of articles in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/categories",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Categorie>> getAllCategories(HttpServletRequest request)
            throws URISyntaxException {
        String token = tokenAuthenticationFilter.getJwtFromRequest(request);
        tokenProvider.validateToken(token);
        log.debug("REST request to get a list of Categories");
        List<Categorie> categories = categorieService.listerCategorie();
        HttpHeaders headers = HeaderUtil.getListAlert("That is the Set of categories after /api/categories ",String.valueOf(categories.size()));
        return new ResponseEntity<>(categories, headers, HttpStatus.OK);
    }

    /**
     * GET  /categories/:id : get the "id" categorie.
     *
     * @param id the id of the categorie to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the categorie, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/categories/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categorie> getCategorie(@PathVariable Long id) {
        log.debug("REST request to get Categorie : {}", id);
        Categorie categorie = categorieService.voirCategorie(id);
        return Optional.ofNullable(categorie)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /categories/:id : delete the "id" categories.
     *
     * @param id the id of the categorie to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/categories/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        log.debug("REST request to delete Categorie : {}", id);
        categorieService.supprimerCategorie(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("categorie", id.toString())).build();
    }
}
