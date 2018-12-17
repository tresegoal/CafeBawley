package cm.rst.controller;

import cm.rst.entities.Produit;
import cm.rst.entities.ProduitCommande;
import cm.rst.service.IProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martins on 21/11/2018.
 */
@Controller
@RequestMapping(value = "/panier")
public class PanierController {

    @Autowired
    private IProduitService produitService;
    @RequestMapping(value = "ajouter/{id}", method = RequestMethod.GET)
    public String ajouter(@PathVariable("id") Long id, HttpSession session,Model model,RedirectAttributes redirAttrs) {
        if( session.getAttribute("panier") == null) {
            List<ProduitCommande> panier = new ArrayList<ProduitCommande>();
            panier.add(new ProduitCommande(produitService.voirProduit(id),1));
            session.setAttribute("panier", panier);
            redirAttrs.addFlashAttribute("messagecreate", "le produit  " + panier.get(0).getProduit().getDesignation() + " a été ajouté avec success");

        }
        else {
            List<ProduitCommande> panier = (List<ProduitCommande>) session.getAttribute("panier");
            int index = dejaPresent(id,panier);
            if (index ==-1){
                panier.add(new ProduitCommande(produitService.voirProduit(id),1));
                redirAttrs.addFlashAttribute("messagecreate", "le produit avec l'identifiant " + id + " a été ajouté avec success");
            }else {
                int quantite = panier.get(index).getQuantiteCmd()+1;
                panier.get(index).setQuantiteCmd(quantite);
                redirAttrs.addFlashAttribute("messagecreate", "le produit  " + panier.get(index).getProduit().getDesignation() + " a été ajouté avec success");
            }
            session.setAttribute("panier", panier);
        }
        return "redirect:/panier";
    }

    @RequestMapping(value = "modifier", method = RequestMethod.POST)
    public String modifier(HttpSession session, RedirectAttributes redirAttrs,
                           @RequestParam("qte") String[] qte) {
        List<ProduitCommande> panier = (List<ProduitCommande>) session.getAttribute("panier");
        for (int i=0; i<panier.size(); i++) {
            panier.get(i).setQuantiteCmd(Integer.parseInt(qte[i]));
        }
        session.setAttribute("panier", panier);
        redirAttrs.addFlashAttribute("messagemodifier", "le panier a été mis a jour avec success");

        return "redirect:/panier";
    }

    @RequestMapping(value = "ajouterDetails/{id}", method = RequestMethod.POST)
    public String ajouterDetails(HttpSession session,@PathVariable("id") Long id, RedirectAttributes redirAttrs,
                           @RequestParam("qte") int qte) {
        if( session.getAttribute("panier") == null) {
            List<ProduitCommande> panier = new ArrayList<ProduitCommande>();
            panier.add(new ProduitCommande(produitService.voirProduit(id),qte));
            session.setAttribute("panier", panier);
            redirAttrs.addFlashAttribute("messagecreate", "le produit  " + panier.get(0).getProduit().getDesignation() + " a été ajouté avec success");

        }else {
            List<ProduitCommande> panier = (List<ProduitCommande>) session.getAttribute("panier");
            int index = dejaPresent(id,panier);
            if (index ==-1){
                panier.add(new ProduitCommande(produitService.voirProduit(id),qte));
                redirAttrs.addFlashAttribute("messagecreate", "le produit avec l'identifiant " + id + " a été ajouté avec success");
            }else {
                panier.get(index).setQuantiteCmd(qte);
                redirAttrs.addFlashAttribute("messagecreate", "le produit  " + panier.get(index).getProduit().getDesignation() + " a été ajouté avec success");
            }
            session.setAttribute("panier", panier);
        }
        return "redirect:/panier";
    }

    private double calculerTotal(HttpSession session) {
        List<ProduitCommande> panier = (List<ProduitCommande>) session.getAttribute("panier");
        double totalCmd=0;
        for (int i=0; i<panier.size(); i++) {
             totalCmd +=( panier.get(i).getQuantiteCmd()* panier.get(i).getProduit().getPrixUnitHT());
        }
        return totalCmd;
    }

    private int dejaPresent(Long id,List<ProduitCommande> panier) {
        for (int i=0; i<panier.size(); i++) {
            if (panier.get(i).getProduit().getId()== id) return i;
        }
        return -1;
    }

    @RequestMapping(value = "supprimer/{id}", method = RequestMethod.GET)
    public String supprimer(@PathVariable("id") Long id, HttpSession session,RedirectAttributes redirAttrs) {
        if (session.getAttribute("panier") == null) {
            redirAttrs.addFlashAttribute("messagesupprimer", "le produit avec l'identifiant " +id+ " n'existe pas dans le panier" );
        }else {
            List<ProduitCommande> panier = (List<ProduitCommande>) session.getAttribute("panier");
            int index = dejaPresent(id,panier);
            if (index ==-1){
                redirAttrs.addFlashAttribute("messagesupprimer", "le produit avec l'identifiant " +id+ " n'existe pas dans le panier" );
            }else {
                redirAttrs.addFlashAttribute("messageupdate", "le produit  " + panier.get(index).getProduit().getDesignation() + " a été supprimer avec success");
                panier.remove(index);
                session.setAttribute("panier", panier);
            }

        }
        return "redirect:/panier";
    }


   /* public String compterPanier(HttpSession session) {
        int produit;
        if(session.getAttribute("panier") == null) {
             produit = 0;
        } else {
           produit =
        }
    }*/

    @RequestMapping(value = "/validation")
    public String Validation(HttpSession session,Model model) {
        if(session.getAttribute("email") == null) {
            return "redirect:/login";
        }else {
            return "commande/index";
        }
    }


    @RequestMapping(value = "")
    public String panier(HttpSession session,Model model) {
        if(session.getAttribute("panier") != null) {
            double total= calculerTotal(session);
            model.addAttribute("total",total);
        }

        return "shoping-cart";
    }
}
