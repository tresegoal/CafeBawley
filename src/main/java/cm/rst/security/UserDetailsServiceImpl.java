package cm.rst.security;

import cm.rst.dao.UtilisateurRepository;
import cm.rst.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Fabrice
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Utilisateur user = utilisateurRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Aucun utilisateur possédant cet email trouvé : " + email);
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        //grantedAuthorities.add(new SimpleGrantedAuthority("LOGGED_USER"));
        user.getRoles().forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });

        return new User(user.getEmail(), user.getPassword(), authorities);
    }

}

