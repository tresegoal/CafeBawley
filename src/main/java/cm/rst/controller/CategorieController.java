/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.controller;

import cm.rst.entities.Categorie;
import cm.rst.service.ICategorieService;
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
@RequestMapping(value = "/admincafe/categorie")
public class  CategorieController {
    @Autowired
    private final ICategorieService ics;

    @Autowired
    public CategorieController(ICategorieService ics) {
        this.ics = ics;
    }
    
    @RequestMapping(value = "")
    public String Index(Model model) {
       List<Categorie> categories = ics.listerCategorie();
       model.addAttribute("categories",categories);
        return "Categorie/index";
    }
    
     @RequestMapping(value = "/addCategorie", method = RequestMethod.GET)
    public String Create(Categorie categorie, Model model) {
        model.addAttribute("categorie",categorie);
        return "categorie/create";
    }
    
    @RequestMapping(value = "/saveCategorie", method = RequestMethod.POST)
    public String Store(@ModelAttribute @Valid Categorie categorie,
            BindingResult bindingResult,RedirectAttributes redirAttrs) {
       if (bindingResult.hasErrors()) {
        /*log("errors =" + bindingResult.getAllErrors());
            ModelAndView mav = new ModelAndView();
             mav.getModel().putAll(bindingResult.getModel());
            return mav;*/
           return "redirect:/admincafe/categorie/addCategorie";
       } else {
           ics.creerCategorie(categorie);
            redirAttrs.addFlashAttribute("messagecreate", "la categorie " +categorie.getDesignation()+ " a ete cree avec success" );
           return "redirect:/admincafe/categorie";
       }   
    }
    
    @RequestMapping(value = "/voirCategorie/{id}", method = RequestMethod.GET)
    public String Show(@PathVariable("id") long id, Model model) {
        Categorie categorie = ics.voirCategorie(id);
        model.addAttribute("categorie",categorie);
        return "Categorie/show";
    }
    
    @RequestMapping(value = "/editCategorie/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, Model model) {
        Categorie categorie = ics.voirCategorie(id);
         model.addAttribute("categorie",categorie);
        return "Categorie/edit";
    }
    
    @RequestMapping(value = "/updateCategorie/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute @Valid Categorie c,
            BindingResult bindingResult,RedirectAttributes redirAttrs) {
       if (bindingResult.hasErrors()) {
        //log("errors =" + bindingResult.getAllErrors());
           return "Categorie/edit";
       } else {
           ics.modifierCategorie(c);
            redirAttrs.addFlashAttribute("messageupdate", "la categorie " +c.getDesignation()+ " a ete modifiee avec success" );
           return "redirect:/admincafe/categorie";
       }   
    }
    
    @RequestMapping(value = "/deleteCategorie/{id}", method = RequestMethod.POST)
    public String Destroy(@PathVariable("id") long id, RedirectAttributes redirAttrs) {
       Categorie categorie = ics.voirCategorie(id);
       ics.supprimerCategorie(categorie);
       redirAttrs.addFlashAttribute("messagedelete", "la categorie " +categorie.getDesignation() + " a ete supprimee avec success" );
        return "redirect:/admincafe/categorie";
    }
    
    @RequestMapping(value = "/activeCategorie/{id}", method = RequestMethod.POST)
    public String Active(@PathVariable("id") long id, Model model,RedirectAttributes redirAttrs) {
        Categorie categorie = ics.voirCategorie(id);
        categorie.setActive(true);
        ics.modifierCategorie(categorie);
        redirAttrs.addFlashAttribute("messageupdate", "la categorie " +categorie.getDesignation()+ " a ete activee avec success" );
        return "redirect:/admincafe/categorie";
    }
    
     @RequestMapping(value = "/desactiveCategorie/{id}", method = RequestMethod.POST)
    public String Desactive(@PathVariable("id") long id, Model model,RedirectAttributes redirAttrs) {
        Categorie categorie = ics.voirCategorie(id);
        categorie.setActive(false);
        ics.modifierCategorie(categorie);
        redirAttrs.addFlashAttribute("messageupdate", "la categorie " +categorie.getDesignation()+ " a ete desactivee avec success" );
        return "redirect:/admincafe/categorie";
    }
}
