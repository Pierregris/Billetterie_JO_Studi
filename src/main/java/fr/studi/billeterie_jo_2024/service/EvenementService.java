package fr.studi.billeterie_jo_2024.service;

import java.util.List;

import fr.studi.billeterie_jo_2024.pojo.Evenement;

public interface EvenementService {

	void createEvenement(Evenement evenement);

	// void updateEvenement(String epreuve, Evenement evenement);

	Evenement getEvenementbyEpreuve(String epreuve);

	List<Evenement> getAllEvenements();

	void updateBilletsVendus(int quantite, Evenement evenement);

}
