/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.controller;

import cm.rst.entities.Image;
import cm.rst.entities.Produit;
import cm.rst.service.IImageService;
import cm.rst.service.IProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author Martins
 */
@Controller
@RequestMapping(value = "/admincafe/images")
public class ImageController {
    @Autowired
    private final IImageService iImageService;
    @Autowired
    private final IProduitService produitService;

    @Autowired
    public ImageController(IImageService iImageService, IProduitService produitService) {
        this.iImageService = iImageService;
        this.produitService = produitService;
    }
    
    @RequestMapping(value = "")
    public String Index(Model model) {
       List<Image> images = iImageService.listerImage();
       model.addAttribute("images",images);
        return "Images/index";
    }
    
     @RequestMapping(value = "/addImage", method = RequestMethod.GET)
    public String Create(Image image, Model model) {
         List<Produit> produits = produitService.listerProduit();
        model.addAttribute("image",image);
         model.addAttribute("produits", produits);
        return "Images/create";
    }
    
    @RequestMapping(value = "/saveImage", method = RequestMethod.POST)
    public String Store(@ModelAttribute @Valid Image img,
                        @RequestParam("file") MultipartFile file, Model model,Produit produit,
                        BindingResult bindingResult, RedirectAttributes redirAttrs) {
       if (bindingResult.hasErrors()) {
           return "redirect:/admincafe/images/addImage";
       }
        try {
            String filename = file.getOriginalFilename();
            iImageService.creerImage(file,filename,produit);
            redirAttrs.addFlashAttribute("messagecreate", "l\'image " +img.getFilename()+ " a ete cree avec success" );
        } catch (Exception e) {
            model.addAttribute("message", "Fail! -> uploaded filename: " + file.getOriginalFilename());
        }

           return "redirect:/admincafe/images";

    }
    
    @RequestMapping(value = "/voirImage/{id}", method = RequestMethod.GET)
    public String Show(@PathVariable("id") long id, Model model) {
        Image image = iImageService.voirImage(id);
        model.addAttribute("image",image);
        return "Images/show";
    }
    
    @RequestMapping(value = "/editImage/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, Model model) {
        List<Produit> produits = produitService.listerProduit();
        Image image = iImageService.voirImage(id);
        model.addAttribute("image",image);
        model.addAttribute("produits", produits);
        return "Images/edit";
    }
    
    @RequestMapping(value = "/updateImage/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute @Valid Image img,
                         @RequestParam("file") MultipartFile file, Model model,Produit produit,
                         BindingResult bindingResult,RedirectAttributes redirAttrs) {
       if (bindingResult.hasErrors()) {
        //log("errors =" + bindingResult.getAllErrors());
          return  "redirect:/admincafe/images/editImage";
       }
        try {
            String filename = file.getOriginalFilename();
            iImageService.modifierImage(file,filename,produit,img);
            redirAttrs.addFlashAttribute("messageupdate", "l\'image " +img.getFilename()+ " a ete modifiee avec success" );
        } catch (Exception e) {
            model.addAttribute("message", "Fail! -> uploaded filename: " + file.getOriginalFilename());
        }

           return "redirect:/admincafe/images";

    }
    
    @RequestMapping(value = "/deleteImage/{id}", method = RequestMethod.POST)
    public String Destroy(@PathVariable("id") long id, RedirectAttributes redirAttrs) {
       Image image = iImageService.voirImage(id);
       iImageService.supprimerImage(image);
       redirAttrs.addFlashAttribute("messagedelete", "l\'image " +image.getFilename() + " a ete supprime avec success" );
        return "redirect:/admincafe/images";
    }
    
   /* @RequestMapping(value = "/activeImage/{id}", method = RequestMethod.GET)
    public String Active(@PathVariable("id") long id, Model model,RedirectAttributes redirAttrs) {
        Image image = iImageService.voirImage(id);
        image.setActive(true);
        iImageService.modifierImage(image);
        redirAttrs.addFlashAttribute("messageupdate", "l\'image " +image.getFileName()+ " a ete activee avec success" );
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
