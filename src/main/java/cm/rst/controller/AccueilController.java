package cm.rst.controller;

import cm.rst.entities.Categorie;
import cm.rst.entities.Produit;
import cm.rst.entities.ProduitCommande;
import cm.rst.service.ICategorieService;
import cm.rst.service.IProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Martins on 20/11/2018.
 */
@Controller
public class AccueilController {
    @Autowired
    private IProduitService produitService;

    @Autowired
    private ICategorieService categorieService;

    public AccueilController(IProduitService produitService, ICategorieService categorieService) {
        this.produitService = produitService;
        this.categorieService = categorieService;
    }

    @RequestMapping(value = "")
    public String Accueil(Model model, HttpSession session) {
        Page<Produit> produits = produitService.listeReduiteProduit();
        model.addAttribute("produits",produits);
        return "index";
    }

    @RequestMapping(value = "/produitDetails/{id}")
    public String ProduitDetails(Model model, @PathVariable long id) {
        Produit produit = produitService.voirProduit(id);
        model.addAttribute("produit",produit);
        return "product-detail";
    }

    @RequestMapping(value = "/livraison")
    public String Livraison() {
        return "livraison";
    }

    @RequestMapping(value = "/produit")
    public String Boutique(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Page<Produit> produits = produitService.listerProduit(page, 3);
        int totalPage = produits.getTotalPages();
        int [] pages = new int[totalPage];
        for (int i=0; i<totalPage; i++) pages[i]=i;
        List<Categorie> categories = categorieService.listerCategorie();
        model.addAttribute("categories", categories);
        model.addAttribute("produits", produits);
        model.addAttribute("pages", pages);
        model.addAttribute("pagecourante", page);
        return "product";
    }

    @RequestMapping(value = "/searchProduct", method = RequestMethod.GET)
    public String SearchProduit(Model model,
                                @RequestParam(name = "search-product") String key) {
        List<Produit> searchProds = produitService.listeProduitParMc(key);
       /* int totalPage = searchProds.getTotalPages();
        int [] pages = new int[totalPage];
        for (int i=0; i<totalPage; i++) pages[i]=i;*/
       /* List<Categorie> categories = categorieService.listerCategorie();
        model.addAttribute("categories", categories);*/
        model.addAttribute("searchProds", searchProds);
       /* model.addAttribute("pages", pages);
        model.addAttribute("pagecourante", page);*/
        return "filtres/searchProduct";
    }

    @RequestMapping(value = "/produitParPrix", method = RequestMethod.GET)
    public String ProduitParPrix(Model model, @RequestParam(name = "min") double min,
                                @RequestParam(name = "max") double max,
                                 @RequestParam(name = "page", defaultValue = "0") int page) {
        Page<Produit> prodPrix = produitService.listeProduitParPrix(min,max,page,3);
        int totalPage = prodPrix.getTotalPages();
        int [] pages = new int[totalPage];
        for (int i=0; i<totalPage; i++) pages[i]=i;
        model.addAttribute("prodPrix", prodPrix);
        model.addAttribute("pages", pages);
        model.addAttribute("pagecourante", page);
        return "filtres/produitParPrix";
    }

    @RequestMapping(value = "/admincafe")
    public String AccueilAdmin() {
        return "welcomeAdmin";
    }
}
