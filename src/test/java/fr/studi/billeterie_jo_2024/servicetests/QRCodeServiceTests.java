package fr.studi.billeterie_jo_2024.servicetests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.google.zxing.WriterException;

import fr.studi.billeterie_jo_2024.serviceimpl.QRCodeServiceImpl;

public class QRCodeServiceTests {

	private final QRCodeServiceImpl qrCodeService = new QRCodeServiceImpl();

	@Test
	void testGenerateQRCodeBase64() throws WriterException, IOException {
		String data = "Test QR Code";
		int width = 200;
		int height = 200;

		String result = qrCodeService.generateQRCodeBase64(width, height, data);

		assertThat(result).isNotNull();

		assertThat(result).matches("^[A-Za-z0-9+/=]+$");

		assertThat(result.length()).isGreaterThan(0);
	}
}
