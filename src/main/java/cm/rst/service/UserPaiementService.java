package cm.rst.service;

import cm.rst.entities.UserPaiement;

public interface UserPaiementService {

    UserPaiement findById(Long id);

    void removeById(Long id);
}
