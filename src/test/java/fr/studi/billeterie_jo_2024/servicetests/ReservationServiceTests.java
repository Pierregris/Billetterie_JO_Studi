package fr.studi.billeterie_jo_2024.servicetests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.zxing.WriterException;

import fr.studi.billeterie_jo_2024.dto.AAjouterAuPanierDTO;
import fr.studi.billeterie_jo_2024.pojo.Evenement;
import fr.studi.billeterie_jo_2024.pojo.Offre;
import fr.studi.billeterie_jo_2024.pojo.Reservation;
import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.repository.EvenementRepository;
import fr.studi.billeterie_jo_2024.repository.OffreRepository;
import fr.studi.billeterie_jo_2024.repository.ReservationRepository;
import fr.studi.billeterie_jo_2024.repository.UtilisateurRepository;
import fr.studi.billeterie_jo_2024.service.QRCodeService;
import fr.studi.billeterie_jo_2024.serviceimpl.ReservationServiceImpl;
import fr.studi.billeterie_jo_2024.status.ResultatGetPanier;
import fr.studi.billeterie_jo_2024.status.StatusReservation;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTests {

	private Utilisateur utilisateur = new Utilisateur();
	private Evenement evenement = new Evenement();
	private Offre offre = new Offre();
	private Reservation reservation = new Reservation();

	@Mock
	EvenementRepository evenementRepository;

	@Mock
	UtilisateurRepository utilisateurRepository;

	@Mock
	ReservationRepository reservationRepository;

	@Mock
	OffreRepository offreRepository;

	@Mock
	QRCodeService qrCodeService;

	@InjectMocks
	ReservationServiceImpl reservationService;

	@BeforeEach
	public void setUp() {
		utilisateur.setNom("Dupond");
		utilisateur.setPrenom("Jean");
		utilisateur.setPassword("jeanDupond1234!");
		utilisateur.setMail("jean.dupond@mail.fr");

		evenement.setSport("Basketball");
		evenement.setBilletsVendus(1000);
		evenement.setCapaciteMax(1500);

		offre.setActive(true);
		offre.setDiscount(0.95);
		offre.setNbPlaces(2);
		offre.setNomOffre("DUO");

		reservation.setId(1L);
		reservation.setEvenement(evenement);
		reservation.setOffreChoisie(offre);

		Authentication authentication = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);

		lenient().when(securityContext.getAuthentication()).thenReturn(authentication);
		lenient().when(authentication.getPrincipal()).thenReturn(utilisateur);
		SecurityContextHolder.setContext(securityContext);
	}

	@Test
	public void testAjouterAuPanier() {
		AAjouterAuPanierDTO panierDTO = new AAjouterAuPanierDTO();
		panierDTO.setEvenement_id(1L);
		panierDTO.setNomOffre("DUO");
		panierDTO.setMontant(100F);

		when(offreRepository.findById("DUO")).thenReturn(Optional.of(offre));
		when(evenementRepository.findById(1L)).thenReturn(Optional.of(evenement));

		Reservation panier = reservationService.ajouterAuPanier(panierDTO);

		assertThat(panier.getEvenement()).isEqualTo(evenement);
		assertThat(panier.getOffreChoisie()).isEqualTo(offre);
		assertThat(panier.getMontant()).isEqualTo(100F);
		assertThat(panier.getUtilisateur()).isEqualTo(utilisateur);
		assertThat(panier.getStatusReservation()).isEqualTo(StatusReservation.PANIER);

		assertThat(evenement.getBilletsVendus()).isEqualTo(1002);

	}

	@Test
	public void testGetPanier() {
		reservation.setStatusReservation(StatusReservation.PANIER);
		reservation.setValidite(LocalDateTime.now().plusHours(1));
		when(reservationRepository.findByUtilisateurAndStatusReservation(utilisateur, StatusReservation.PANIER))
				.thenReturn(List.of(reservation));

		ResultatGetPanier result = reservationService.getPanier(utilisateur);
		assertThat(result.getReservations()).isEqualTo(List.of(reservation));
		assertThat(result.getSuppression()).isFalse();
	}

	@Test
	public void testGetPanierExpire() {
		reservation.setStatusReservation(StatusReservation.PANIER);
		reservation.setValidite(LocalDateTime.now().minusHours(1));
		when(reservationRepository.findByUtilisateurAndStatusReservation(utilisateur, StatusReservation.PANIER))
				.thenReturn(new ArrayList(List.of(reservation)));
		when(reservationRepository.findById(reservation.getId())).thenReturn(Optional.of(reservation));

		ResultatGetPanier result = reservationService.getPanier(utilisateur);
		assertThat(result.getReservations()).isEmpty();
		assertThat(result.getSuppression()).isTrue();
	}

	@Test
	public void testValiderReservation() {
		when(reservationRepository.findById(reservation.getId())).thenReturn(Optional.of(reservation));

		reservationService.validerReservation(reservation.getId());

		ArgumentCaptor<Reservation> captorReservation = ArgumentCaptor.forClass(Reservation.class);
		verify(reservationRepository).save(captorReservation.capture());
		Reservation reservationValidee = captorReservation.getValue();

		assertThat(reservationValidee.getStatusReservation()).isEqualTo(StatusReservation.FINALISEE);
	}

	@Test
	public void testGenererCleAchat() {
		when(reservationRepository.findById(reservation.getId())).thenReturn(Optional.of(reservation));

		reservationService.genererCleAchat(reservation.getId());

		ArgumentCaptor<Reservation> captorReservation = ArgumentCaptor.forClass(Reservation.class);
		verify(reservationRepository).save(captorReservation.capture());
		Reservation reservationValidee = captorReservation.getValue();

		assertThat(reservationValidee.getCléAchat()).isNotNull();
	}

//	@Override
//	public String genererQRCode(UUID cleAchat, UUID utilisateur_id, int width, int height) {
//		try {
//			return qrCodeService.generateQRCodeBase64(width, height, cleAchat.toString() + utilisateur_id.toString());
//		} catch (WriterException | IOException e) {
//			return e.toString();
//		}

	@Test
	public void testGenererQRCode() throws WriterException, IOException {
		UUID cleAchat = UUID.randomUUID();
		UUID utilisateurId = UUID.randomUUID();
		String qrCodeBase64 = "data:image/png;base64,...";

		when(qrCodeService.generateQRCodeBase64(200, 200, cleAchat.toString() + utilisateurId.toString()))
				.thenReturn(qrCodeBase64);

		String result = reservationService.genererQRCode(cleAchat, utilisateurId, 200, 200);

		assertThat(result).isEqualTo(qrCodeBase64);
	}

	@Test
	public void testGenererQRCode_Erreur() throws WriterException, IOException {
		UUID cleAchat = UUID.randomUUID();
		UUID utilisateurId = UUID.randomUUID();

		when(qrCodeService.generateQRCodeBase64(200, 200, cleAchat.toString() + utilisateurId.toString()))
				.thenThrow(new WriterException("erreur"));

		String result = reservationService.genererQRCode(cleAchat, utilisateurId, 200, 200);

		assertThat(result).contains("erreur");
	}

	@Test
	void testGetReservationsFinalisees() {
		reservation.setStatusReservation(StatusReservation.FINALISEE);
		List<Reservation> reservationsFinalisees = new ArrayList<>();
		reservationsFinalisees.add(reservation);

		when(reservationRepository.findByUtilisateurAndStatusReservation(utilisateur, StatusReservation.FINALISEE))
				.thenReturn(reservationsFinalisees);

		List<Reservation> result = reservationService.getReservationsFinalisees(utilisateur);

		assertThat(result).hasSize(1).containsExactly(reservation);
	}

	@Test
	void testValiderPanier() {
		List<Reservation> panier = new ArrayList<>();
		panier.add(reservation);

		reservationService.validerPanier(panier);

		assertThat(reservation.getStatusReservation()).isEqualTo(StatusReservation.FINALISEE);

	}
}
