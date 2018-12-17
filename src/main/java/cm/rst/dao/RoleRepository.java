package cm.rst.dao;

import cm.rst.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByRoleName(String roleName);
}
