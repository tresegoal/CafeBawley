package cm.rst.mobileController;

import cm.rst.entities.Commande;
import cm.rst.entities.Utilisateur;
import cm.rst.service.IAccountService;
import cm.rst.service.ICommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fabrice
 */
@RestController
@RequestMapping("/api")
public class MobileController {

    @Autowired
    private IAccountService accountService;
    @Autowired
    private ICommandeService commandeService;

    @GetMapping("/commands")
    public List<Commande> allUserCommands(String email) {

        List<Commande> clientCommands;

        Utilisateur user = accountService.findUserByEmail(email);
        if (user != null && user.isActive())
            clientCommands = (List<Commande>)user.getCommandes();
        else
            clientCommands = new ArrayList<>();

        return clientCommands;
    }

    /*@RequestMapping(value = "/commandes")
    public List<Commande> Index(Model model) {
        return (List<Commande>)commandeService.listerCommande();
    }*/

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public Commande Show(@PathVariable("id") long id, Model model) {
        return commandeService.voirCommande(id);
    }
}
