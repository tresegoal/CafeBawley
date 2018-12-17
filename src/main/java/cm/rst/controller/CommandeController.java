/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.controller;

import cm.rst.entities.Commande;
import cm.rst.service.ICommandeService;
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
@RequestMapping(value = "/admincafe/commande")
public class CommandeController {
    @Autowired
    private final ICommandeService ics;

    @Autowired
    public CommandeController(ICommandeService ics) {
        this.ics = ics;
    }
    
    @RequestMapping(value = "")
    public String Index(Model model) {
       List<Commande> commandes = ics.listerCommande();
       model.addAttribute("commandes",commandes);
        return "Commande/index";
    }
    
     @RequestMapping(value = "/addCommande", method = RequestMethod.GET)
    public String Create(Commande commande, Model model) {
        model.addAttribute("commande",commande);
        return "Commande/create";
    }
    
    @RequestMapping(value = "/saveCommande", method = RequestMethod.POST)
    public String Store(@ModelAttribute @Valid Commande commande,
            BindingResult bindingResult,RedirectAttributes redirAttrs) {
       if (bindingResult.hasErrors()) {
        /*log("errors =" + bindingResult.getAllErrors());
            ModelAndView mav = new ModelAndView();
             mav.getModel().putAll(bindingResult.getModel());
            return mav;*/
           return "redirect:/admincafe/commande/addCommande";
       } else {
           ics.creerCommande(commande);
            redirAttrs.addFlashAttribute("messagecreate", "la commande " +commande.getId()+ " a ete cree avec success" );
           return "redirect:/admincafe/commande";
       }   
    }
    
    @RequestMapping(value = "/voirCommande/{id}", method = RequestMethod.GET)
    public String Show(@PathVariable("id") long id, Model model) {
        Commande commande = ics.voirCommande(id);
        model.addAttribute("commande",commande);
        return "Commande/show";
    }
    
    @RequestMapping(value = "/editCommande/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, Model model) {
        Commande commande = ics.voirCommande(id);
        model.addAttribute("commande",commande);
        return "Commande/edit";
    }
    
    @RequestMapping(value = "/updateCommande/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute @Valid Commande cmd,
            BindingResult bindingResult,RedirectAttributes redirAttrs) {
       if (bindingResult.hasErrors()) {
        //log("errors =" + bindingResult.getAllErrors());
           return "redirect:/admincafe/commande/edit";
       } else {
           ics.modifierCommande(cmd);
            redirAttrs.addFlashAttribute("messageupdate", "la commande " +cmd.getId()+ " a ete modifie avec success" );
           return "redirect:/admincafe/commande";
       }   
    }
    
    @RequestMapping(value = "/deleteCommande/{id}", method = RequestMethod.GET)
    public String Destroy(@PathVariable("id") long id, RedirectAttributes redirAttrs) {
       Commande commande = ics.voirCommande(id);
       ics.supprimerCommande(commande);
       redirAttrs.addFlashAttribute("messagedelete", "la commande " +commande.getId() + " a ete supprime avec success" );
        return "redirect:/admincafe/commande";
    }
    
    /*@RequestMapping(value = "/activeImage/{id}", method = RequestMethod.GET)
    public String Active(@PathVariable("id") long id, Model model,RedirectAttributes redirAttrs) {
        Commande commande = ics.voirCommande(id);
        commande.setActive(true);
        ics.modifierCommande(commande);
        redirAttrs.addFlashAttribute("messageupdate", "la commande " +image.getFileName()+ " a ete activee avec success" );
        return "Images/index";
    }
    
     @RequestMapping(value = "/desactiveImage/{id}", method = RequestMethod.GET)
    public String Desactive(@PathVariable("id") long id, Model model,RedirectAttributes redirAttrs) {
        Image image = iImageService.voirImage(id);
        image.setActive(false);
        iImageService.modifierImage(image);
        redirAttrs.addFlashAttribute("messageupdate", "l\'image " +image.getFileName()+ " a ete desactivee avec success" );
        return "Images/index";
    }*/
}
