// encryption program pseudo code
import java.io.*;
public class Encryption {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out, true)) {

            // initialize line and passkey variables
            String passkey = null;
            String line;
            while ((line = reader.readLine()) != null) {
                if ("QUIT".equals(line)) break;

                String[] parts = line.split(" ", 2);
                // action = word before first space
                String action = parts[0];
                String message;
                if (parts.length > 1) {
                    message = parts[1];
                } else {
                    message = "";
                }

                // if command was PASS
                // Output RESULT password set to driver
                if (action.equals("PASS")) {
                    passkey = message;
                    // Output RESULT password set to driver
                    writer.println("RESULT password " + passkey + " set" );
                } else if (action.equals("ENCRYPT")) {
                    // call encrypt vignere function with passkey and string as argument, put result in string
                    writer.println("RESULT " + vigenereEncrypt(message, passkey));
                } else if (action.equals("DECRYPT")) {
                    // call encrypt vignere function with passkey and string as argument, put result in string
                    writer.println("RESULT " + vigenereDecrypt(message, passkey));
                } else {
                    writer.println("ERROR Invalid command");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String vigenereDecrypt(String message, String passkey) {
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

    private static String vigenereEncrypt(String message, String passkey) {
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
}


