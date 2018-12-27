package cm.rst.dao;

import cm.rst.entities.UserPaiement;
import org.springframework.data.repository.CrudRepository;

public interface UserPaiementRepository extends CrudRepository<UserPaiement, Long> {
}
