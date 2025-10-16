package fr.studi.billeterie_jo_2024.servicetests;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import fr.studi.billeterie_jo_2024.serviceimpl.EmailServiceImpl;

public class EmailServiceTests {

	@Mock
	private JavaMailSender javaMailSender;

	@InjectMocks
	private EmailServiceImpl emailService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSendEmail() {
		String destinataire = "test@example.com";
		String objet = "Test Objet";
		String texte = "Ceci est un test";

		emailService.sendEmail(destinataire, objet, texte);

		verify(javaMailSender).send(org.mockito.ArgumentMatchers
				.argThat((SimpleMailMessage message) -> destinataire.equals(message.getTo()[0])
						&& objet.equals(message.getSubject()) && texte.equals(message.getText())));
	}
}
