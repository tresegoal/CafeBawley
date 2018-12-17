package cm.rst.serviceImpl;

import cm.rst.dao.RoleRepository;
import cm.rst.entities.Role;
import cm.rst.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements ICrudService<Role, Long> {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> getAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role add(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role update(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void delete(Long id) {
		roleRepository.delete(id);
	}

	@Override
	public Role findE(Long id) {
		return roleRepository.findOne(id);
	}
}
