package cm.rst.serviceImpl;

import cm.rst.dao.UserLivraisonRepository;
import cm.rst.entities.UserLivraison;
import cm.rst.service.UserLivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLivraisonServiceImpl implements UserLivraisonService {

    @Autowired
    private UserLivraisonRepository livraisonRepository;

    @Override
    public UserLivraison findById(Long id) {
        return livraisonRepository.findOne(id);
    }

    @Override
    public void removeById(Long id) {
        livraisonRepository.delete(id);
    }
}
