package fr.studi.billeterie_jo_2024.serviceimpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import fr.studi.billeterie_jo_2024.service.QRCodeService;

@Service
public class QRCodeServiceImpl implements QRCodeService {

	@Override
	public String generateQRCodeBase64(int width, int height, String stringToConvert)
			throws WriterException, IOException {

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(stringToConvert, BarcodeFormat.QR_CODE, width, height);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
		return Base64.getEncoder().encodeToString(outputStream.toByteArray());
	}

}
