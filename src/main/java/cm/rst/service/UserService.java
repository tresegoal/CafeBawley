package cm.rst.service;

import cm.rst.entities.*;
import cm.rst.security.PasswordResetToken;

import java.util.Set;

public interface UserService {
    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordResetTokenForUser(final Utilisateur user, final String token);

    Utilisateur findByUsername(String username);

    Utilisateur findByEmail(String email);

    Utilisateur createUser(Utilisateur user, Set<UserRole> userRoles) throws Exception;

    Utilisateur save(Utilisateur user);

    void updateUserBilling(UserFacture userFacture, UserPaiement userPaiement, Utilisateur user);

    void updateUserShipping(UserLivraison userLivraison, Utilisateur user);

    void setUserDefaultPayment(Long userPaymentId, Utilisateur user);

    void setUserDefaultShipping(Long userShippingId, Utilisateur user);
}