package jp.pokepay.pokepaylib;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES128 {

    private byte[] aesSecretKey;
    private byte[] aesInitVector;

    public AES128(String key, String iv) {
        aesSecretKey = key.getBytes(StandardCharsets.UTF_8);
        aesInitVector = iv.getBytes(StandardCharsets.UTF_8);
    }

    public byte[] encode(String code) throws ProcessingError {
        byte[] encodeBytes = null;
        try {
            final SecretKeySpec keySpec = new SecretKeySpec(aesSecretKey, "AES");
            final IvParameterSpec ivSpec = new IvParameterSpec(aesInitVector);
            final String algorithm = "AES/CBC/PKCS7Padding";
            final Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            encodeBytes = cipher.doFinal(code.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new ProcessingError("BLE AES encoding error: " + e.getMessage());
        }
        return encodeBytes;
    }

    public String decode(byte[] code) throws ProcessingError {
        byte[] decodeBytes = null;
        try {
            final SecretKeySpec keySpec = new SecretKeySpec(aesSecretKey, "AES");
            final IvParameterSpec ivSpec = new IvParameterSpec(aesInitVector);
            final String algorithm = "AES/CBC/PKCS7Padding";
            final Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            decodeBytes = cipher.doFinal(code);
        } catch (Exception e) {
            throw new ProcessingError("BLE AES decoding error: " + e.getMessage());
        }
        return new String(decodeBytes, StandardCharsets.UTF_8);
    }
}
