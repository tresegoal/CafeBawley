package cm.rst.controller;

import cm.rst.entities.Adresse;
import cm.rst.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Fabrice
 */
@Controller
@RequestMapping("/adresse")
public class AdresseController{
	
	@Autowired
	private ICrudService<Adresse, Long> adresseService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String getAllAdress(Model model) {
        model.addAttribute("adresses", adresseService.getAll());
        return "adresses";
    }
	
    @RequestMapping(value="/nouveau", method = RequestMethod.GET)
    public String createAdress(Model model) {
        model.addAttribute("adresse", new Adresse());
        return "adressForm";
    }

    @RequestMapping(value="/enregistrer", method = RequestMethod.POST)
    public String saveAdress(Adresse adresse) {
    	adresseService.add(adresse);
        return "redirect:/adresses";
    }


    @RequestMapping(value="/editer/{id}", method = RequestMethod.GET)
    public String editAdress(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("adresse", adresseService.findE(id));
        return "adressForm";
    }

    @RequestMapping(value="/supprimer/{id}", method = RequestMethod.GET)
    public String deleteAdress(@PathVariable(name = "id") Long id) {
    	adresseService.delete(id);;
        return "redirect:/adresses";
    }
	
}
