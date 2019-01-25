package cm.rst.dao;

import cm.rst.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByUsername(String username);

    Utilisateur findByEmail(String email);

   // Utilisateur findByUsernameOrEmail(String query);

   // Utilisateur findByUsernameAndEmail(String query);
}
