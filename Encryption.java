// encryption program
// Gets a string from driver program and can either set it as a password
// encrypt it or decrypt it using the password via vignere cipher
// sends result/error messages back to driver program
import java.io.*;
public class Encryption {

    /**
     * this function uses vignere cipher to decrypt a given string from user
     * @param message String to decrypt
     * @param passkey String key that decrypts the message
     * @return String that is decrypted
     */
    private static String vignereDecrypt(String message, String passkey) {
        // define an empty string to store decrypted word
        StringBuilder builder = new StringBuilder();
        // repeat the passkey, so it is longer or equal to the word to encrypt
        passkey = passkey.repeat((message.length() / passkey.length()) + 1);
        // loop from i = 0 to length of string to encrypt
        for (int i = 0; i < message.length(); i++) {
            // figure length of shift
            int shift = passkey.charAt(i) - 'A';
            // apply shift to message letter
            char newLetter = (char) ('A' + (((message.charAt(i) - 'A') - shift + 26) % 26));
            // append new letter to empty string
            builder.append(newLetter);
        }

        return builder.toString();
    }

    /**
     * this function uses vignere cipher to encrypt a given string from user
     * @param message String to encrypt
     * @param passkey String key that encrypts the messaage
     * @return String that is encrypted
     */
    private static String vignereEncrypt(String message, String passkey) {
        // define an empty string to store encrypted word
        StringBuilder builder = new StringBuilder();
        // repeat the passkey so it is longer or equal to the word to encrypt
        passkey = passkey.repeat((message.length() / passkey.length()) + 1);
        // loop from i = 0 to length of string to encrypt
        for (int i = 0; i < message.length(); i++) {
            // figure length of shift
            int shift = passkey.charAt(i) - 'A';
            // apply shift to message letter
            char newLetter = (char) ('A' + (((message.charAt(i) - 'A') + shift) % 26));
            // append new letter to empty string
            builder.append(newLetter);
        }

        return builder.toString();
    }
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out, true)) {

            // initialize line and passkey variables
            String passkey = null;
            String line;

            // loop through user input as long as there is user input
            while ((line = reader.readLine()) != null) {
                // if quit then break from loop
                if ("QUIT".equals(line)) break;

                // split the line into action (word before first space) and message (after first space)
                String[] parts = line.split(" ", 2);
                String action = parts[0];
                String message;
                if (parts.length > 1) {
                    message = parts[1];
                } else {
                    message = "";
                }

                // if command was PASS
                if (action.equals("PASS")) {
                    passkey = message;
                    // Output RESULT password set to driver
                    writer.println("RESULT password set" );
                } else if (action.equals("ENCRYPT")) {
                    // if passkey is empty (no password was set) print error
                    if (passkey == null) {
                        writer.println("ERROR Password not set");
                    } else {
                        // call encrypt vignere function with passkey and string as argument, put result in string
                        writer.println("RESULT " + vignereEncrypt(message, passkey));
                    }
                } else if (action.equals("DECRYPT")) {
                    // if passkey is empty (no password was set) print error
                    if (passkey == null) {
                        writer.println("ERROR Password not set");
                    } else {
                        // call encrypt vignere function with passkey and string as argument, put result in string
                        writer.println("RESULT " + vignereDecrypt(message, passkey));
                    }
                } else {
                    writer.println("ERROR Invalid command");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


