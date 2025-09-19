package fr.studi.billeterie_jo_2024.service;

import java.util.List;

import fr.studi.billeterie_jo_2024.pojo.Evenement;

public interface EvenementService {

	void createEvenement(Evenement evenement);

	List<Evenement> getAllEvenements();

	List<Evenement> getEvenementsBySport(String sport);

	void updateBilletsVendus(int quantite, Evenement evenement);

	List<String> getDistinctSports();

}
