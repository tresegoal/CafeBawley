package cm.rst.serviceImpl;

import cm.rst.dao.UserRepository;
import cm.rst.entities.Utilisateur;
import cm.rst.restController.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String query) throws UsernameNotFoundException {
        Utilisateur user = userRepository.findByUsernameOrEmail(query,query);

        if (null == user) {
            throw new CustomException("Invalid username or email/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return user;
    }


}


