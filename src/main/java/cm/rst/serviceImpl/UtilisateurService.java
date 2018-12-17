package cm.rst.serviceImpl;

import cm.rst.dao.RoleRepository;
import cm.rst.dao.UtilisateurRepository;
import cm.rst.entities.Utilisateur;
import cm.rst.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService implements ICrudService<Utilisateur, Long> {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<Utilisateur> getAll() {
		return utilisateurRepository.findAll();
	}

	@Override
	public Utilisateur add(Utilisateur utilisateur) {
		String hashPWD = passwordEncoder.encode(utilisateur.getPassword());
		utilisateur.setPassword(hashPWD);
		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public Utilisateur update(Utilisateur utilisateur) {
		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public void delete(Long id) {
		utilisateurRepository.delete(id);
	}

	@Override
	public Utilisateur findE(Long id) {
		return utilisateurRepository.findOne(id);
	}
}
