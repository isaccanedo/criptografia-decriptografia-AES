package br.com.isaccanedo;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author Isac Canedo - Encriptação e Decriptação AES / ECB
 */

public class Crypto {

    public static String Encrypt(String data, String key) {

        if (!isKeyLengthValid(key)) {

            try {
                throw new Exception("Secret Key's must be 128, 192 or 256 bits");

            }catch (Exception e) {
                e.printStackTrace();
            }

        }

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encrypted = cipher.doFinal(data.getBytes("UTF-8"));
            Base64.Encoder encoder = java.util.Base64.getEncoder();
            return new String(encoder.encodeToString(encrypted));

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    public static String Decrypt(String data, String key) {
        try {
            Base64.Decoder decoder = java.util.Base64.getDecoder();
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] original = cipher.doFinal(decoder.decode(data.getBytes("UTF-8")));
            return new String(original);

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    /**
     * @param key - Verifica se o tamanho da chave secreta é válida
     * @return - Retorna TRUE se a chave secreta for VÁLIDA, caso contrário retorna FALSE
     */
    private static boolean isKeyLengthValid(String key) {
        return key.length() == 16 || key.length() == 24 || key.length() == 32;
    }
}