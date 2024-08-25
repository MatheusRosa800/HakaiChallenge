import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Main {
 
    // Valores esperados 
    private static final String SECRET_KEY = "StrongKey!987654";
    private static final String EXPECTED_ENCRYPTED_MAIN_ACTIVITY_HASH = "2kAtnuDB/Gcr3tTyYCTGnFAZMU++Zn71NOfljS3bB61cfrNxStJUGDa6nwMGooWdb1V/4HHHB9e+rcWh6TZw+U6dikNtumlBhgkAzlMd/Rw=";
    
    public static void main(String[] args) {
        // Caminhos para os arquivos
        String mainActivityPath = "C:\\Users\\matro\\Documents\\NetBeansProjects\\JavaApplication43\\src\\teste.java"; // Substitua com o caminho correto
        
        // Calcular o hash dos arquivos
        String mainActivityHash = getFileHash(mainActivityPath, "SHA-256");
        
        System.out.println("MainActivity Hash: " + mainActivityHash);
       
        String encryptedMainActivity = encrypt(mainActivityHash, SECRET_KEY);
        
        System.out.println("Encrypted MainActivity Hash: " + encryptedMainActivity);
        
        // Verificar integridade
        if (!EXPECTED_ENCRYPTED_MAIN_ACTIVITY_HASH.equals(encryptedMainActivity)) {
            System.out.println("Integrity encrypted check failed.");
             // Encerrar o programa
        } else {
            System.out.println("Integrity encrypted check passed.");
            //  início normal da aplicação
        }

    }

    // Método para calcular a hash do arquivo
    private static String getFileHash(String filePath, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            InputStream inputStream = new FileInputStream(filePath);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
            inputStream.close();
            byte[] hashBytes = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Erro: Algoritmo de hash não encontrado - " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo - " + e.getMessage());
            return null;
        }
    }
    
    private static String encrypt(String data, String key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
