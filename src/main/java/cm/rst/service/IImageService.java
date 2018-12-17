/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.service;

import cm.rst.entities.Image;
import cm.rst.entities.Produit;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author Martins
 */
public interface IImageService {
    public List<Image> listerImage(int pageNumber, int pageSize); 
    public List<Image> listerImage(); 
    public Image voirImage(Long id);
    public Resource loadFile(String filename);
    public Image creerImage(MultipartFile file, String filename,Produit produit);
    public Image modifierImage(MultipartFile file, String filename,Produit produit, Image image);
    public void supprimerImage(Long id);
    public void supprimerImage(Image image);
    public void init();
    public Stream<Path> loadFiles();
}
