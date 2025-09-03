package dev.joaountura.payments_project.payment.services;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Locale;


@Service
public class PixService {

    private static final int POLYNOMIAL = 0x1021;

    public byte[] gerarQRCode(BigDecimal value, String key, String name, String city) throws WriterException, IOException {
        int largura = 300;
        int altura = 300;
        String payload = generatePayload(value, key, name, city);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        var bitMatrix = qrCodeWriter.encode(payload, BarcodeFormat.QR_CODE, largura, altura);
        BufferedImage buffer = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(buffer, "PNG", baos);
        return baos.toByteArray();
    }

    private String generatePayload(BigDecimal value, String key, String name, String city) {

        String nome = name.length() > 25 ? name.substring(0, 25) : name;
        String txid = "0";


        String subcampo00 = "0014BR.GOV.BCB.PIX";
        String subcampo01 = "01" + String.format("%02d", key.length()) + key;
        String merchantInfo = subcampo00 + subcampo01;
        String field26 = "26" + String.format("%02d", merchantInfo.getBytes(StandardCharsets.UTF_8).length) + merchantInfo;

        // Valor
        String valorFormatado = String.format(Locale.US, "%.2f", value);


        String field62 = "62" + String.format("%02d", 4 + txid.getBytes(StandardCharsets.UTF_8).length)
                + "05" + String.format("%02d", txid.getBytes(StandardCharsets.UTF_8).length) + txid;


        String payloadSemCRC = ""
                + "000201"
                + field26
                + "52040000"
                + "5303986"
                + "54" + String.format("%02d", valorFormatado.getBytes(StandardCharsets.UTF_8).length) + valorFormatado
                + "5802BR59" + String.format("%02d", nome.getBytes(StandardCharsets.UTF_8).length) + nome
                + "60" + String.format("%02d", city.getBytes(StandardCharsets.UTF_8).length) + city
                + field62
                + "6304"; // CRC placeholder


        String crc = calculateCRC16(payloadSemCRC);

        return payloadSemCRC + crc;
    }

    private String calculateCRC16(String payload) {
        int crc = 0xFFFF;
        byte[] bytes = payload.getBytes(StandardCharsets.UTF_8);

        for (byte b : bytes) {
            crc ^= (b & 0xFF) << 8;
            for (int i = 0; i < 8; i++) {
                if ((crc & 0x8000) != 0) {
                    crc = (crc << 1) ^ POLYNOMIAL;
                } else {
                    crc <<= 1;
                }
                crc &= 0xFFFF;
            }
        }

        return String.format("%04X", crc);
    }
}
