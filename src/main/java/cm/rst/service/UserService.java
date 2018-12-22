package cm.rst.service;

import cm.rst.entities.UserRole;
import cm.rst.entities.Utilisateur;
import cm.rst.security.PasswordResetToken;

import java.util.Set;

public interface UserService {
    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordResetTokenForUser(final Utilisateur user, final String token);

    Utilisateur findByUsername(String username);

    Utilisateur findByEmail(String email);

    Utilisateur createUser(Utilisateur user, Set<UserRole> userRoles) throws Exception;

    Utilisateur save(Utilisateur user);
}