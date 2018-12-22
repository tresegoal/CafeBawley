package cm.rst.serviceImpl;

import cm.rst.dao.PasswordResetTokenRepository;
import cm.rst.dao.RoleRepository;
import cm.rst.dao.UserRepository;
import cm.rst.entities.UserRole;
import cm.rst.entities.Utilisateur;
import cm.rst.security.PasswordResetToken;
import cm.rst.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

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

}