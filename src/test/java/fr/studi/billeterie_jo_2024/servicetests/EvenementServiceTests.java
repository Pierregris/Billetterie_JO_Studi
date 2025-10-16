package fr.studi.billeterie_jo_2024.servicetests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.studi.billeterie_jo_2024.pojo.Evenement;
import fr.studi.billeterie_jo_2024.pojo.Reservation;
import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.repository.EvenementRepository;
import fr.studi.billeterie_jo_2024.serviceimpl.EvenementServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EvenementServiceTests {
	private Utilisateur utilisateur = new Utilisateur();
	private Evenement evenement = new Evenement();
	private Reservation reservation = new Reservation();

	@Mock
	EvenementRepository evenementRepository;

	@InjectMocks
	EvenementServiceImpl evenementService;

	@BeforeEach
	public void setUp() {
		utilisateur.setNom("Dupond");
		utilisateur.setPrenom("Jean");
		utilisateur.setPassword("jeanDupond1234!");
		utilisateur.setMail("jean.dupond@mail.fr");

		evenement.setSport("Basketball");
		evenement.setBilletsVendus(1000);
		evenement.setCapaciteMax(1500);
	}

	@Test
	public void testCreateEvenement() {
		evenementService.createEvenement(evenement);

		ArgumentCaptor<Evenement> captorEvenement = ArgumentCaptor.forClass(Evenement.class);
		verify(evenementRepository).save(captorEvenement.capture());
		Evenement evenementCree = captorEvenement.getValue();

		assertThat(evenementCree).isEqualTo(evenement);
	}

	@Test
	public void testUpdateBilletsVendus() {
		Integer billetsVendusBase = evenement.getBilletsVendus();
		evenementService.updateBilletsVendus(10, evenement);
		assertThat(evenement.getBilletsVendus()).isEqualTo(billetsVendusBase + 10);
	}

	@Test
	void testGetAllEvenements() {
		List<Evenement> evenements = List.of(evenement);
		when(evenementRepository.findAll()).thenReturn(evenements);

		List<Evenement> result = evenementService.getAllEvenements();
		assertThat(result).isEqualTo(evenements);
	}

	@Test
	void testGetEvenementsBySport() {
		List<Evenement> evenements = List.of(evenement);
		when(evenementRepository.findBySport("Basketball")).thenReturn(evenements);

		List<Evenement> result = evenementService.getEvenementsBySport("Basketball");
		assertThat(result).isEqualTo(evenements);
	}

	@Test
	void testGetDistinctSports() {
		List<String> sports = List.of("Basketball", "Football");
		when(evenementRepository.findAllSports()).thenReturn(sports);

		List<String> result = evenementService.getDistinctSports();
		assertThat(result).isEqualTo(sports);
	}
}
