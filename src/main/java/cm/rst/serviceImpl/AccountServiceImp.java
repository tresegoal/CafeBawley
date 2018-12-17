package cm.rst.serviceImpl;

import cm.rst.dao.RoleRepository;
import cm.rst.dao.UtilisateurRepository;
import cm.rst.entities.Utilisateur;
import cm.rst.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp implements IAccountService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private RoleRepository roleRepository;

   /* @Override
    public void addRoleToUser(String email, String roleName) {

        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        Role role = roleRepository.findByRoleName(roleName);

        utilisateur.getRoles().add(role);
        utilisateurRepository.save(utilisateur);
    }*/

    @Override
    public Utilisateur findUserByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }
}
