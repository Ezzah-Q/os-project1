// driver program, pseudocode
import java.io.*;
import java.util.*;

public class Driver {
    public static void main(String[] args) throws Exception {
        // accept single command line argument, name of log file
        String file = args[0];

        // create two new processes (one for logger and one for encryption)
        // for logger use filename as an argument
        ProcessBuilder logger = new ProcessBuilder("java", "logger", file);
        ProcessBuilder encryptor = new ProcessBuilder("java", "encryption");

        // launch logger process command and build one way connection to logger (output)
        Process loggerProcess = logger.start();
        PrintWriter logWriter = new PrintWriter(loggerProcess.getOutputStream(), true);

        // launch encryptor process command and build two-way communication (input and output)
        Process encryptProcess = encryptor.start();
        BufferedReader cryptReader = new BufferedReader(new InputStreamReader(encryptProcess.getInputStream()));
        PrintWriter cryptWriter = new PrintWriter(encryptProcess.getOutputStream(), true);

        // start driver program --> send this to logger
        logWriter.println("START Driver started.");

        // create arraylist to hold the history
        ArrayList<String> history = new ArrayList<>();

        // set up scanner to get user input
        Scanner sc = new Scanner(System.in);

        // loop
        label:
        while(true) {
            System.out.println("Command Menu:");
            System.out.println("1. Password");
            System.out.println("2. Encrypt");
            System.out.println("3. Decrypt");
            System.out.println("4. View History");
            System.out.println("5. Quit Program");
            System.out.println("------------------");
            System.out.println("Please Enter a Command");

            // read command
            String command = sc.nextLine().trim().toLowerCase();
            // send command to logger

            // if user chooses password

            switch (command) {
                case "password": {
                    // prompt user to either use history string or enter new one
                    System.out.println("Would you like to...");
                    System.out.println("1. Choose old password from history");
                    System.out.println("2. Or create a new one? ");
                    System.out.println("Please type 1 or 2: ");
                    // store user input
                    String number = sc.nextLine().trim();
                    // if user chooses history
                    if (number.equals("1")) {
                        // prompt user to choose one using the index
                        System.out.println("Type in number of word you want to choose...");
                        // display the history array as a list
                        System.out.println("History: ");
                        int length = history.size();
                        for (int i = 0; i < length; i++) {
                            System.out.println((i + 1) + ") " + history.get(i));
                        }
                        System.out.print("Number: ");
                        int index = Integer.parseInt(sc.nextLine().trim()) - 1;
                        // set the chosen word as password
                        String password = history.get(index);
                        cryptWriter.println("PASS " + password);
                    } else if (number.equals("2")) {
                        System.out.print("Enter a password: ");
                        // read and set the password
                        String passkey = sc.nextLine().toUpperCase();
                        // send password to encryption program "PASS" + password
                        cryptWriter.println("PASS " + passkey);
                    }
                    // read response from encryption program
                    String result = cryptReader.readLine();
                    if (Objects.equals(result, "RESULT")) {
                        // send encryption response to user and logger
                        System.out.println("Password set successfully");
                        logWriter.println("PASSWORD Password set.");
                    } else if (Objects.equals(result, "ERROR")) {
                        // send encryption response to user and logger
                        System.out.println("Password was not set successfully");
                        logWriter.println("PASSWORD Password set failed.");
                    }

                    break;
                }
                case "encrypt": {
                    // prompt user to either use history string or enter new one
                    System.out.println("Would you like to...");
                    System.out.println("1. Choose old string from history");
                    System.out.println("2. Or create a new one? ");
                    System.out.println("Please type 1 or 2: ");

                    String number = sc.nextLine().trim();
                    if (number.equals("1")) {
                        // prompt user to choose one using the index
                        System.out.println("Type in number of word you want to choose...");
                        // display the history array as a list
                        System.out.println("History: ");
                        int length = history.size();
                        for (int i = 0; i < length; i++) {
                            System.out.println((i + 1) + ") " + history.get(i));
                        }
                        System.out.print("Number: ");
                        int index = Integer.parseInt(sc.nextLine().trim()) - 1;
                        // set the chosen word as string to encrypt
                        String encrypt = history.get(index);
                        cryptWriter.println("ENCRYPT " + encrypt);
                    } else if (number.equals("2")) {
                        System.out.print("Enter a string to encrypt: ");
                        // read and set the password
                        String encrypt = sc.nextLine().toUpperCase();
                        // send string to encryption program
                        cryptWriter.println("ENCRYPT " + encrypt);
                        // record string in history
                        history.add(encrypt);
                    }
                    // read response from encryption program
                    String result = cryptReader.readLine();
                    if (Objects.equals(result, "RESULT")) {
                        // send encryption response to user and logger
                        System.out.println("Encryption successful");
                        logWriter.println("ENCRYPT " + result);
                    } else if (Objects.equals(result, "ERROR")) {
                        // send encryption response to user and logger
                        System.out.println("Encryption not successful");
                        logWriter.println("ENCRYPT encrypting failed");
                    }
                    break;
                }
                case "decrypt": {
                    // prompt user to either use history string or enter new one
                    System.out.println("Would you like to...");
                    System.out.println("1. Choose old string from history");
                    System.out.println("2. Or create a new one? ");
                    System.out.println("Please type 1 or 2: ");

                    String number = sc.nextLine().trim();
                    if (number.equals("1")) {
                        // prompt user to choose one using the index
                        System.out.println("Type in number of word you want to choose...");
                        // display the history array as a list
                        System.out.println("History: ");
                        int length = history.size();
                        for (int i = 0; i < length; i++) {
                            System.out.println((i + 1) + ") " + history.get(i));
                        }
                        System.out.print("Number: ");
                        int index = Integer.parseInt(sc.nextLine().trim()) - 1;
                        // set the chosen word as string to encrypt
                        String decrypt = history.get(index);
                        cryptWriter.println("DECRYPT " + decrypt);
                    } else if (number.equals("2")) {
                        System.out.print("Enter a string to decrypt: ");
                        // read and set the password
                        String decrypt = sc.nextLine().toUpperCase();
                        // send string to encryption program
                        cryptWriter.println("DECRYPT " + decrypt);
                        // record string in history
                        history.add(decrypt);
                    }
                    // read response from encryption program
                    String result = cryptReader.readLine();
                    if (Objects.equals(result, "RESULT")) {
                        // send encryption response to user and logger
                        System.out.println("Decryption successful");
                        logWriter.println("DECRYPT " + result);
                    } else if (Objects.equals(result, "ERROR")) {
                        // send encryption response to user and logger
                        System.out.println("Decryption not successful");
                        logWriter.println("DECRYPT encrypting failed");
                    }
                    break;
                }
                case "history":
                    // display the history array as a list
                    System.out.println("History: ");
                    int length = history.size();
                    for (int i = 0; i < length; i++) {
                        System.out.println((i + 1) + ") " + history.get(i));
                    }
                    logWriter.println("HISTORY Viewed history");
                    break;
                case "quit":
                    logWriter.println("QUIT");
                    cryptWriter.println("QUIT");
                    break label;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }

        logWriter.println("EXIT Driver exited.");
        sc.close();
    }
}
