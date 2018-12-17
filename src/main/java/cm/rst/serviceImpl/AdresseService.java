package cm.rst.serviceImpl;

import cm.rst.dao.AdresseRepository;
import cm.rst.entities.Adresse;
import cm.rst.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdresseService implements ICrudService<Adresse, Long> {

	@Autowired
	private AdresseRepository adresseRepository;

	@Override
	public List<Adresse> getAll() {
		return adresseRepository.findAll();
	}

	@Override
	public Adresse add(Adresse adresse) {
		return adresseRepository.save(adresse);
	}

	@Override
	public Adresse update(Adresse adresse) {
		return adresseRepository.save(adresse);
	}

	@Override
	public void delete(Long id) {
		adresseRepository.delete(id);
	}

	@Override
	public Adresse findE(Long id) {
		return adresseRepository.findOne(id);
	}
}
