package cm.rst.controller;

import cm.rst.entities.ProduitCommande;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Martins on 20/12/2018.
 */

@ControllerAdvice
public class AnnotationAdvice {
    @ModelAttribute("quantite")
    public int Quantite(HttpSession session) {
        List<ProduitCommande> panier = (List<ProduitCommande>) session.getAttribute("panier");
        int quantite = 0;
        if (session.getAttribute("panier") == null) {
            quantite = 0;
        }else {
            for (int i=0; i<panier.size(); i++) {
                quantite += panier.get(i).getQuantiteCmd();
            }
        }
        return quantite;
    }
}
