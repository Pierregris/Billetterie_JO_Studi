package fr.studi.billeterie_jo_2024.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.studi.billeterie_jo_2024.pojo.Offre;
import fr.studi.billeterie_jo_2024.repository.OffreRepository;
import fr.studi.billeterie_jo_2024.service.OffreService;

@Service
public class OffreServiceImpl implements OffreService {

	@Autowired
	OffreRepository offreRepository;

	@Override
	public void desactiverOffre(String nomOffre) {
		System.out.println("Entrée fonction");
		Offre offre = offreRepository.findById(nomOffre).orElse(null);
		offre.setActive(false);
		offreRepository.save(offre);
	}

	@Override
	public void activerOffre(String nomOffre) {
		Offre offre = offreRepository.findById(nomOffre).orElse(null);
		offre.setActive(true);
		offreRepository.save(offre);
	}

}
