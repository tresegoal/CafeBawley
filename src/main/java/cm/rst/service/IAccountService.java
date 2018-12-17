package cm.rst.service;

import cm.rst.entities.Utilisateur;

/**
 * @author Fabrice
 */
public interface IAccountService {

//    public void addRoleToUser(String email, String roleName);
    public Utilisateur findUserByEmail(String email);
}
