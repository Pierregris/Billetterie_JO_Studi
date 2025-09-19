package fr.studi.billeterie_jo_2024.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.studi.billeterie_jo_2024.pojo.Evenement;
import fr.studi.billeterie_jo_2024.repository.EvenementRepository;
import fr.studi.billeterie_jo_2024.service.EvenementService;

@Service
public class EvenementServiceImpl implements EvenementService {

	@Autowired
	EvenementRepository evenementRepository;

	@Override
	public void createEvenement(Evenement evenement) {
		evenementRepository.save(evenement);

	}

	@Override
	public void updateBilletsVendus(int quantite, Evenement evenement) {
		evenement.setBilletsVendus(evenement.getBilletsVendus() + quantite);
	}

	@Override
	public List<Evenement> getAllEvenements() {
		return evenementRepository.findAll();
	}

	@Override
	public List<Evenement> getEvenementsBySport(String sport) {
		return evenementRepository.findBySport(sport);
	}

	public List<String> getDistinctSports() {
		return evenementRepository.findAllSports();
	};

}
