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

import fr.studi.billeterie_jo_2024.pojo.Offre;
import fr.studi.billeterie_jo_2024.repository.OffreRepository;
import fr.studi.billeterie_jo_2024.serviceimpl.OffreServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OffreServiceTests {

	@Mock
	OffreRepository offreRepository;

	@InjectMocks
	OffreServiceImpl offreService;

	private Offre offre;

	@BeforeEach
	void setUp() {
		offre = new Offre();
		offre.setNomOffre("DUO");
		offre.setActive(true);
	}

	@Test
	void testDesactiverOffre() {
		when(offreRepository.findById("DUO")).thenReturn(Optional.of(offre));

		offreService.desactiverOffre("DUO");

		ArgumentCaptor<Offre> captor = ArgumentCaptor.forClass(Offre.class);
		verify(offreRepository).save(captor.capture());
		Offre savedOffre = captor.getValue();

		assertThat(savedOffre.getActive()).isFalse();
	}

	@Test
	void testActiverOffre() {
		offre.setActive(false);
		when(offreRepository.findById("DUO")).thenReturn(Optional.of(offre));

		offreService.activerOffre("DUO");

		ArgumentCaptor<Offre> captor = ArgumentCaptor.forClass(Offre.class);
		verify(offreRepository).save(captor.capture());
		Offre savedOffre = captor.getValue();

		assertThat(savedOffre.getActive()).isTrue();
	}
}
