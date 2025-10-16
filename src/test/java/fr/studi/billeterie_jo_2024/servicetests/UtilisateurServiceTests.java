package fr.studi.billeterie_jo_2024.servicetests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.repository.UtilisateurRepository;
import fr.studi.billeterie_jo_2024.serviceimpl.UtilisateurServiceImpl;
import fr.studi.billeterie_jo_2024.status.Role;

@ExtendWith(MockitoExtension.class)
public class UtilisateurServiceTests {

	@Mock
	UtilisateurRepository utilisateurRepository;

	@InjectMocks
	UtilisateurServiceImpl utilisateurService;

	private Utilisateur utilisateur = new Utilisateur();

	@BeforeEach
	public void setUp() {
		utilisateur.setNom("Dupond");
		utilisateur.setPrenom("Jean");
		utilisateur.setPassword("jeanDupond1234!");
		utilisateur.setMail("jean.dupond@mail.fr");
	}

	@Test
	public void testCreateUtilisateur() {
		utilisateurService.createUtilisateur(utilisateur);

		ArgumentCaptor<Utilisateur> captorUtilisateur = ArgumentCaptor.forClass(Utilisateur.class);
		verify(utilisateurRepository).save(captorUtilisateur.capture());

		Utilisateur utilisateurCree = captorUtilisateur.getValue();

		assertThat(utilisateurCree.getRole()).isEqualTo(Role.USER);
		assertThat(utilisateurCree.getActivationToken()).isNotNull();

	}

	@Test
	public void testGetUtilisateurByMail() {
		when(utilisateurRepository.findByMail(utilisateur.getMail())).thenReturn(Optional.of(utilisateur));

		Utilisateur utilisateurTrouve = utilisateurService.getUtilisateurbyMail(utilisateur.getMail());
		assertThat(utilisateurTrouve).isEqualTo(utilisateur);

	}
}
