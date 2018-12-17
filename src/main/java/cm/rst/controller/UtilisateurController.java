package cm.rst.controller;

import cm.rst.entities.Utilisateur;
import cm.rst.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Fabrice
 */
@Controller
@RequestMapping("/admincafe/utilisateur")
//@Secured(value = {"ROLE_ADMIN"})
public class UtilisateurController {

    @Autowired
    private ICrudService<Utilisateur, Long> utilisateurService;

    @RequestMapping(value = {"","/"})
    public String getAllUser(Model model) {
        List<Utilisateur> utilisateurs = utilisateurService.getAll();

      /*  if(utilisateurs == null)
            utilisateurs = new ArrayList<Utilisateur>();*/

        model.addAttribute("utilisateurs", utilisateurService.getAll());
        return "user/index";
    }

    @RequestMapping(value = "/nouveau")
    public String createUser(Model model, Utilisateur utilisateur) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "user/create";
    }

    @RequestMapping(value = "/enregistrer", method = RequestMethod.POST)
    public String saveUser(@Validated Utilisateur utilisateur, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "user/create";

        utilisateurService.add(utilisateur);

        return "redirect:/admincafe/utilisateur";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, Model model) {
        Utilisateur user = utilisateurService.findE(id);
        model.addAttribute("utilisateur",user);
        return "user/edit";
    }

    @RequestMapping(value = "/modifierUser/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute @Valid Utilisateur user,
                         BindingResult bindingResult, RedirectAttributes redirAttrs) {
        if (bindingResult.hasErrors()) {
            return "user/edit";
        } else {
            utilisateurService.update(user);
            redirAttrs.addFlashAttribute("message update", "l'utilisateur " +user.getNom()+ " a ete modifie avec success" );
            return "redirect:/admincafe/utilisateur";
        }
    }

    @RequestMapping(value = "/supprimer/{id}", method = RequestMethod.POST)
    public String Destroy(@PathVariable("id") long id, RedirectAttributes redirAttrs) {
        Utilisateur user = utilisateurService.findE(id);
        utilisateurService.delete(id);
        redirAttrs.addFlashAttribute("Suppression", "l'utilisateur " +user.getEmail() + " a ete supprime avec success" );
        return "redirect:/admincafe/utilisateur";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String Show(@PathVariable("id") long id, Model model) {
        Utilisateur user = utilisateurService.findE(id);
        model.addAttribute("produit",user);
        return "user/details";
    }

    @RequestMapping(value = "/activeUser/{id}", method = RequestMethod.POST)
    public String Active(@PathVariable("id") long id, Model model,RedirectAttributes redirAttrs) {
        Utilisateur user = utilisateurService.findE(id);
        user.setActive(true);
        utilisateurService.update(user);
        redirAttrs.addFlashAttribute("Activation", "l'utilisateur " +user.getNom()+ " a ete activee avec success" );
        return "redirect:/admincafe/utilisateur";
    }

    @RequestMapping(value = "/desactiveUser/{id}", method = RequestMethod.POST)
    public String Desactive(@PathVariable("id") long id, Model model, RedirectAttributes redirAttrs) {
        Utilisateur user = utilisateurService.findE(id);
        user.setActive(false);
        utilisateurService.update(user);
        redirAttrs.addFlashAttribute("Désactivation", "l'utilisateur " +user.getNom()+ " a ete desactivee avec success" );
        return "redirect:/admincafe/utilisateur";
    }

}
