/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.rst.serviceImpl;

import cm.rst.dao.ImageRepository;
import cm.rst.entities.Image;
import cm.rst.entities.Produit;
import cm.rst.exception.FileStorageException;
import cm.rst.exception.MyFileNotFoundException;
import cm.rst.service.IImageService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Martins
 */
@Service
@Transactional
public class ImageServiceImpl implements IImageService{
    
    @Autowired
    private ImageRepository imageRepository;
    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get("src/main/resources/static/image");


    @Override
    public List<Image> listerImage(int pageNumber, int pageSize) {
         return imageRepository.findAll(new PageRequest(pageNumber, pageSize)).getContent();
    }

    @Override
    public List<Image> listerImage() {
        return imageRepository.findAll();
    }

    @Override
    public Image voirImage(Long fileId) {
        return imageRepository.findOne(fileId);

    }

    @Override
    public Image creerImage(MultipartFile file,String filename, Produit produit) {

        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
            Image img = new Image(filename, produit);

            return imageRepository.save(img);

        } catch (Exception e) {
            throw new RuntimeException("Echec! -> message = " + e.getMessage());
        }


    }

    @Override
    public Image modifierImage(MultipartFile file,String filename, Produit produit,Image img) {

        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
            img.setFilename(filename);
            img.setProduit(produit);
            return imageRepository.save(img);
        } catch (Exception e) {
            throw new RuntimeException("Echec! -> message = " + e.getMessage());
        }


    }


    @Override
    public void supprimerImage(Long id) {
        imageRepository.delete(id);
        // FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void supprimerImage(Image image) {
         imageRepository.delete(image);
       // FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else{
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error! -> message = " + e.getMessage());
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

    @Override
    public Stream<Path> loadFiles() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new RuntimeException("\"Failed to read stored file");
        }
    }
}
