package cm.rst.controller;

import cm.rst.dao.UtilisateurRepository;
import cm.rst.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class LoggedInUserController {

    @Autowired
    private UtilisateurRepository userRepository;

    @ModelAttribute("loggedInUser")
    public void secureUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur user = userRepository.findByEmail(auth.getName());
        model.addAttribute("loggedInUser", user);
    }

    @GetMapping("/secure/user")
    public String securePage() {
        return "secure/user";
    }

}
