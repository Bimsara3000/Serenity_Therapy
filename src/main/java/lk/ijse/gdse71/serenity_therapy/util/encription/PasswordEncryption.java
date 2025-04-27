package lk.ijse.gdse71.serenity_therapy.util.encription;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryption {
    private static final String PEPPER = "secure1234";

    public static String encryptPassword(String plainPassword) {
        String pepperedPassword = plainPassword + PEPPER;
        return BCrypt.hashpw(pepperedPassword, BCrypt.gensalt());
    }
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        String pepperedPassword = plainPassword + PEPPER;
        return BCrypt.checkpw(pepperedPassword, hashedPassword);
    }
}
