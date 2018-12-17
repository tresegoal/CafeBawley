/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.controller;

import cm.rst.entities.Categorie;
import cm.rst.entities.Produit;
import cm.rst.service.ICategorieService;
import cm.rst.service.IProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author Martins
 */
@Controller
@RequestMapping(value = "/admincafe/produit")
public class ProduitController {
    @Autowired
    private final IProduitService iProduitService;
    private final ICategorieService categorieService;

    @Autowired
    public ProduitController(IProduitService iProduitService, ICategorieService categorieService) {
        this.iProduitService = iProduitService;
        this.categorieService = categorieService;
    }
    
    @RequestMapping(value = "")
    public String Index(Model model) {
        List<Produit> produits = iProduitService.listerProduit();
        model.addAttribute("produits",produits);
        return "Produit/index";
    }
    
     @RequestMapping(value = "/addProduit", method = RequestMethod.GET)
    public String Create(Produit produit, Model model) {
         List<Categorie> categories = categorieService.listerCategorie();
         model.addAttribute("produit",produit);
         model.addAttribute("categories",categories);
        return "Produit/create";
    }
    
    @RequestMapping(value = "/saveProduit", method = RequestMethod.POST)
    public String Store(@ModelAttribute @Valid Produit produit,
            BindingResult bindingResult,RedirectAttributes redirAttrs) {
       if (bindingResult.hasErrors()) {
        /*log("errors =" + bindingResult.getAllErrors());
            ModelAndView mav = new ModelAndView();
             mav.getModel().putAll(bindingResult.getModel());
            return mav;*/
           return "redirect:/admincafe/produit/addProduit";
       } else {
           iProduitService.creerProduit(produit);
            redirAttrs.addFlashAttribute("messagecreate", "le produit " +produit.getDesignation()+ " a ete cree avec success" );
           return "redirect:/admincafe/produit";
       }   
    }
    
    @RequestMapping(value = "/voirProduit/{id}", method = RequestMethod.GET)
    public String Show(@PathVariable("id") long id, Model model) {
        Produit produit = iProduitService.voirProduit(id);
        model.addAttribute("produit",produit);
        return "Produit/show";
    }
    
    @RequestMapping(value = "/editProduit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, Model model) {
        List<Categorie> categories = categorieService.listerCategorie();
        Produit produit = iProduitService.voirProduit(id);
        model.addAttribute("produit",produit);
        model.addAttribute("categories",categories);
        return "Produit/edit";
    }
    
    @RequestMapping(value = "/updateProduit/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute @Valid Produit p,
            BindingResult bindingResult,RedirectAttributes redirAttrs) {
       if (bindingResult.hasErrors()) {
        //log("errors =" + bindingResult.getAllErrors());
           return  "redirect:/admincafe/produit/editProduit";
       } else {
           iProduitService.modifierProduit(p);
            redirAttrs.addFlashAttribute("messageupdate", "le produit " +p.getDesignation()+ " a ete modifie avec success" );
           return "redirect:/produit";
       }   
    }
    
    @RequestMapping(value = "/deleteProduit/{id}", method = RequestMethod.POST)
    public String Destroy(@PathVariable("id") long id, RedirectAttributes redirAttrs) {
       Produit produit = iProduitService.voirProduit(id);
       iProduitService.supprimerProduit(produit);
       redirAttrs.addFlashAttribute("messagedelete", "le produit " +produit.getDesignation() + " a ete supprime avec success" );
        return "redirect:/admincafe/produit";
    }
    
    @RequestMapping(value = "/activeProduit/{id}", method = RequestMethod.POST)
    public String Active(@PathVariable("id") long id, Model model,RedirectAttributes redirAttrs) {
        Produit produit = iProduitService.voirProduit(id);
        produit.setActive(true);
        iProduitService.modifierProduit(produit);
        redirAttrs.addFlashAttribute("messageupdate", "le produit " +produit.getDesignation()+ " a ete activee avec success" );
        return "redirect:/admincafe/produit";
    }
    
     @RequestMapping(value = "/desactiveProduit/{id}", method = RequestMethod.POST)
    public String Desactive(@PathVariable("id") long id, Model model,RedirectAttributes redirAttrs) {
        Produit produit = iProduitService.voirProduit(id);
        produit.setActive(false);
        iProduitService.modifierProduit(produit);
        redirAttrs.addFlashAttribute("messageupdate", "le produit " +produit.getDesignation()+ " a ete desactivee avec success" );
        return "redirect:/admincafe/produit";
    }
}
