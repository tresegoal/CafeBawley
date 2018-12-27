package cm.rst.service;

import cm.rst.entities.UserLivraison;

public interface UserLivraisonService {
    UserLivraison findById(Long id);

    void removeById(Long id);
}
