package fr.studi.billeterie_jo_2024.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import fr.studi.billeterie_jo_2024.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender javaMailSender;

	@Override
	public void sendEmail(String destinataire, String objet, String texte) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(destinataire);
		message.setSubject(objet);
		message.setText(texte);
		javaMailSender.send(message);
	}

}
