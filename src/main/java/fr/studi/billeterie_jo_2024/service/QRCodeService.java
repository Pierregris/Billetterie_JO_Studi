package fr.studi.billeterie_jo_2024.service;

import java.io.IOException;

import com.google.zxing.WriterException;

public interface QRCodeService {

	public String generateQRCodeBase64(int width, int height, String stringToConvert)
			throws WriterException, IOException;
}
