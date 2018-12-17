package cm.rst.controller;

import cm.rst.entities.Utilisateur;
import cm.rst.service.IAccountService;
import cm.rst.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ICrudService<Utilisateur, Long> utilisateurService;

    @RequestMapping("/infos/{id}")
    public String infos(@PathVariable("id") long id, Model model){
        Utilisateur user = utilisateurService.findE(id);
        model.addAttribute("user", user);
        return "client/infos";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String updateProfile(@PathVariable("id") long id, Model model) {
        Utilisateur user = utilisateurService.findE(id);
        model.addAttribute("utilisateur",user);
        return "user/editProfile";
    }

    /*public String adressesClient(Model model, @PathVariable("id") long id){
        Set<Adresse> adresses = new HashSet<>();
        Utilisateur user = utilisateurService.findE(id);


        return "";
    }*/


    @RequestMapping("/signup-error")
    public String loginError(Model model) {
        model.addAttribute("signup-error", true);
        return "enregistrer";
    }
}
