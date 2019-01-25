package cm.rst.serviceImpl;

import cm.rst.dao.UserRepository;
import cm.rst.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
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
        Utilisateur user = userRepository.findByUsername(query);

        if (null == user) {
            throw new UsernameNotFoundException("Element introuvable");
        }

        return user;
    }


}


