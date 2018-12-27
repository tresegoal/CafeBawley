package cm.rst.serviceImpl;

import cm.rst.dao.UserPaiementRepository;
import cm.rst.entities.UserPaiement;
import cm.rst.service.UserPaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPaiementServiceImpl implements UserPaiementService {

    @Autowired
    private UserPaiementRepository userPaiementRepository;

    @Override
    public UserPaiement findById(Long id) {
        return userPaiementRepository.findOne(id);
    }

    @Override
    public void removeById(Long id) {
        userPaiementRepository.delete(id);
    }
}
