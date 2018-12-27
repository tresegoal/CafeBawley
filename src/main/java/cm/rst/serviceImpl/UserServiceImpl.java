package cm.rst.serviceImpl;

import cm.rst.dao.*;
import cm.rst.entities.*;
import cm.rst.security.PasswordResetToken;
import cm.rst.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserPaiementRepository userPaymentRepository;

    @Autowired
    private UserLivraisonRepository userShippingRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordResetTokenForUser(final Utilisateur user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
    }

    @Override
    public Utilisateur findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Utilisateur createUser(Utilisateur user, Set<UserRole> userRoles) {
        Utilisateur localUser = userRepository.findByUsername(user.getUsername());

        if (localUser != null) {
            LOG.info("user {} already exists. Nothing will be done.", user.getUsername());
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);

            localUser = userRepository.save(user);
        }

        return localUser;
    }

    @Override
    public Utilisateur save(Utilisateur user) {
        return userRepository.save(user);
    }

    @Override
    public void updateUserBilling(UserFacture userFacture, UserPaiement userPaiement, Utilisateur user) {
        userPaiement.setUser(user);
        userPaiement.setUserBilling(userFacture);
        userPaiement.setDefaultPayment(true);
        userFacture.setUserPaiement(userPaiement);
        user.getUserPaymentList().add(userPaiement);
        save(user);
    }

    @Override
    public void updateUserShipping(UserLivraison userLivraison, Utilisateur user) {
        userLivraison.setUser(user);
        userLivraison.setUserShippingDefault(true);
        user.getUserShippingList().add(userLivraison);
        save(user);
    }

    @Override
    public void setUserDefaultPayment(Long userPaymentId, Utilisateur user) {
        List<UserPaiement> userPaymentList = (List<UserPaiement>) userPaymentRepository.findAll();

        for (UserPaiement userPayment : userPaymentList) {
            if (userPayment.getId() == userPaymentId) {
                userPayment.setDefaultPayment(true);
                userPaymentRepository.save(userPayment);
            } else {
                userPayment.setDefaultPayment(false);
                userPaymentRepository.save(userPayment);
            }
        }
    }

    @Override
    public void setUserDefaultShipping(Long userShippingId, Utilisateur user) {
        List<UserLivraison> userShippingList = (List<UserLivraison>) userShippingRepository.findAll();

        for (UserLivraison userShipping : userShippingList) {
            if (userShipping.getId() == userShippingId) {
                userShipping.setUserShippingDefault(true);
                userShippingRepository.save(userShipping);
            } else {
                userShipping.setUserShippingDefault(false);
                userShippingRepository.save(userShipping);
            }
        }
    }

    /*private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordResetTokenForUser(final Utilisateur user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
    }

    @Override
    public Utilisateur findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Utilisateur createUser(Utilisateur user, Set<UserRole> userRoles) {
        Utilisateur localUser = userRepository.findByUsername(user.getUsername());

        if (localUser != null) {
            LOG.info("user {} already exists. Nothing will be done.", user.getUsername());
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);

            localUser = userRepository.save(user);
        }

        return localUser;
    }

    @Override
    public Utilisateur save(Utilisateur user) {
        return userRepository.save(user);
    }

    @Override
    public void updateUserBilling(UserFacture userFacture, UserPaiement userPaiement, Utilisateur user) {

    }

    @Override
    public void updateUserShipping(UserLivraison userLivraison, Utilisateur user) {

    }

    @Override
    public void setUserDefaultPayment(Long userPaymentId, Utilisateur user) {

    }

    @Override
    public void setUserDefaultShipping(Long userShippingId, Utilisateur user) {

    }*/

}