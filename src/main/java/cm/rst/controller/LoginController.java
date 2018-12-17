package cm.rst.controller;

import cm.rst.autologin.Autologin;
import cm.rst.dao.UtilisateurRepository;
import cm.rst.entities.Utilisateur;
import cm.rst.providers.FacebookProvider;
import cm.rst.providers.GoogleProvider;
import cm.rst.service.IAccountService;
import cm.rst.service.ICrudService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    FacebookProvider facebookProvider;

    @Autowired
    GoogleProvider googleProvider;

    @Autowired
    private UtilisateurRepository userRepository;

    @Autowired
    private ICrudService<Utilisateur, Long> utilisateurService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Autologin autologin;

    @RequestMapping(value = "/facebook", method = RequestMethod.GET)
    public String loginToFacebook(Model model) {
        return facebookProvider.getFacebookUserData(model, new Utilisateur());
    }

    @RequestMapping(value = "/google", method = RequestMethod.GET)
    public String loginToGoogle(Model model) {
        return googleProvider.getGoogleUserData(model, new Utilisateur());
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistration(Model model, Utilisateur registerForm) {
        model.addAttribute("registerForm", new Utilisateur());
        return "enregistrer";
    }

    /*@PostMapping("/registration")
    public String registerUser(HttpServletResponse httpServletResponse, Model model, @Valid Utilisateur userBean, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userBean.setProvider("REGISTRATION");

        // Sauvegardé en BD
        if (StringUtils.isNotEmpty(userBean.getPassword())) {
            userBean.setPassword(bCryptPasswordEncoder.encode(userBean.getPassword()));
        }
        utilisateurService.add(userBean);

        autologin.setSecuritycontext(userBean);

        model.addAttribute("loggedInUser", userBean);
        return "secure/user";
    }*/

/*
    @RequestMapping("/register")
    public String newClient(Model model) {
        model.addAttribute("registerForm", new Utilisateur());
        return "enregistrer";
    }*/

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(HttpServletResponse httpServletResponse, Model model, @Valid Utilisateur userBean, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        Utilisateur utilisateur = userRepository.findByEmail(userBean.getEmail());

        if (utilisateur != null) {
            model.addAttribute("loggedInUser", userBean);
            return "secure/user";
        }
        ;

        if (!userBean.getPassword().equals(userBean.getPasswordConfirm()))
            throw new RuntimeException("Vous devez confirmer votre mot de passe");

        userBean.setProvider("ROLE_CLIENT");

        // Sauvegardé en BD
        if (StringUtils.isNotEmpty(userBean.getPassword())) {
            userBean.setPassword(bCryptPasswordEncoder.encode(userBean.getPassword()));
        }

//        accountService.addRoleToUser(userBean.getEmail(), "ROLE_CLIENT");

        utilisateurService.add(userBean);

        autologin.setSecuritycontext(userBean);

        model.addAttribute("loggedInUser", userBean);
        return "secure/user";
    }

    @RequestMapping(value = "/connecte", method = RequestMethod.POST)
    public String connecte(Model model, Utilisateur userBean) {

        Utilisateur user = accountService.findUserByEmail(userBean.getEmail());

        if (user == null) throw new RuntimeException("L'utilisateur n'existe pas");

        model.addAttribute("loggedInUser", userBean);
        return "secure/user";
    }

    /**
     * En cas d'erreur
     */
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping("/403")
    public String accessDenied(){
        return "403";
    }

    @RequestMapping("/404")
    public String notFound(){
        return "404";
    }

}
