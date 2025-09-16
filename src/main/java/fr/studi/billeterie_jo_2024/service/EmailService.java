package fr.studi.billeterie_jo_2024.service;

public interface EmailService {
	public void sendEmail(String destinataire, String objet, String body);
}
