package br.com.isaccanedo;

public class TestCrypto {

    public static void main(String[] args) {

        Crypto crypto = new Crypto();
        String data   = "Isac Canedo";
        String key    = "123MX5612YT3456123PLKM63";

        System.out.println("+=======================================================");
        System.out.println("| Dados em claro: " + data);
        String encriptdata = crypto.Encrypt(data, key);
        System.out.println("| Dados encriptados ==> " + encriptdata);
        System.out.println("+=======================================================");

        String decriptData = crypto.Decrypt(encriptdata, key);
        System.out.println("+=======================================================");
        System.out.println("| Resultado da decriptografia:");
        System.out.println("| " + decriptData);
        System.out.println("+=======================================================");

    }

}
