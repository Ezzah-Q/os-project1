// Driver program interacts with user and communicates with processes via pipes
import java.io.*;
import java.util.*;
public class Driver {
    // function that displays the history of the program
    private static void displayHistory(ArrayList<String> history) {
        System.out.println("          History ");
        System.out.println("------------------------------");
        for (int i = 0; i < history.size(); i++) {
            System.out.println((i + 1) + ") " + history.get(i));
        }
        System.out.println("------------------------------");
    }

    // function that asks user if they want string from history
    private static String getStringFromHistoryOrNew(Scanner sc, ArrayList<String> history, String prompt) {
        while (true) {
            System.out.print(prompt + " (Y/N): ");
            String choice = sc.nextLine().trim();

            if (choice.equalsIgnoreCase("y")) {
                if (history.isEmpty()) {
                    System.out.println("History is empty. Please choose no.");
                    continue;
                }
                displayHistory(history);
                System.out.print("Type in number of word you want to choose: ");
                int index = Integer.parseInt(sc.nextLine().trim()) - 1;
                return history.get(index);
            } else if (choice.equalsIgnoreCase("n")) {
                System.out.print("Enter a string: ");
                return sc.nextLine().toUpperCase();
            } else {
                System.out.println("Invalid choice. Please type y or n.");
            }
        }
    }
    public static void main(String[] args) throws Exception {
        // accept single command line argument, name of log file
        String file = args[0];

        // create two new processes; the logger one accepts filename as argument
        ProcessBuilder logger = new ProcessBuilder("java", "Logger", file);
        ProcessBuilder encryptor = new ProcessBuilder("java", "Encryption");

        // launch logger process command and build output connection to logger
        Process loggerProcess = logger.start();
        PrintWriter logWriter = new PrintWriter(loggerProcess.getOutputStream(), true);

        // launch encryptor process command and build input and output communication
        Process encryptProcess = encryptor.start();
        BufferedReader cryptReader = new BufferedReader(new InputStreamReader(encryptProcess.getInputStream()));
        PrintWriter cryptWriter = new PrintWriter(encryptProcess.getOutputStream(), true);

        // start the driver program --> send this to logger
        logWriter.println("START Driver started.");

        // create arraylist to hold the history
        ArrayList<String> history = new ArrayList<>();

        // set up scanner to get user input
        Scanner sc = new Scanner(System.in);

        label:
        while(true) {
            System.out.println("           Menu");
            System.out.println("------------------------------");
            System.out.println("1. Password - set password");
            System.out.println("2. Encrypt - encrypt word");
            System.out.println("3. Decrypt - decrypt word");
            System.out.println("4. History - show history");
            System.out.println("5. Quit - stop program");
            System.out.println("------------------------------");
            System.out.print("Please Enter a Command: ");

            String command = sc.nextLine().trim().toLowerCase();
            System.out.println();

            switch (command) {
                case "password": {
                    // have logger print our command
                    logWriter.println("SET_PASSWORD Setting password.");
                    String password = getStringFromHistoryOrNew(sc, history, "Would you like to choose password from history?");
                    // send password to encryption program and read response from it
                    cryptWriter.println("PASS " + password);
                    String result = cryptReader.readLine();

                    if (result.startsWith("RESULT")) {
                        System.out.println("Password set successfully");
                        logWriter.println("PASSWORD Password set."); // if possible add stalling time
                        System.out.println();
                    } else if (result.startsWith("ERROR")) {
                        System.out.println("Password was not set successfully");
                        logWriter.println("PASSWORD Password set failed.");
                        System.out.println();
                    }
                    break;
                }

                case "encrypt": {
                    String encrypt = getStringFromHistoryOrNew(sc, history, "Would you like to choose a string from history?");
                    // write to logger and encryption program
                    logWriter.println("ENCRYPT " + encrypt);
                    cryptWriter.println("ENCRYPT " + encrypt);

                    // only add if not already in history
                    if (!history.contains(encrypt)) {
                        history.add(encrypt);
                    }

                    // read response from encryption program
                    String result = cryptReader.readLine();
                    if (result.startsWith("RESULT")) {
                        System.out.println("Encryption successful " + result);
                        history.add(result);
                        logWriter.println("ENCRYPT Success: " + result);
                        System.out.println();
                    } else if (result.startsWith("ERROR")) {
                        System.out.println("Encryption not successful");
                        logWriter.println("ENCRYPT Encrypting failed"); // error for if passkey is not set before encrypting
                        System.out.println();
                    }
                    break;
                }

                case "decrypt": {
                    String decrypt = getStringFromHistoryOrNew(sc, history, "Would you like to choose a string from history?");
                    // write to logger and encryption program
                    logWriter.println("DECRYPT " + decrypt);
                    cryptWriter.println("DECRYPT " + decrypt);

                    // only add if not already in history
                    if (!history.contains(decrypt)) {
                        history.add(decrypt);
                    }

                    // read response from encryption program
                    String result = cryptReader.readLine();
                    if (result.startsWith("RESULT")) {
                        System.out.println("Decryption successful " + result);
                        history.add(result);
                        logWriter.println("DECRYPT " + result);
                    } else if (result.startsWith("ERROR")) {
                        System.out.println("Decryption not successful");
                        logWriter.println("DECRYPT encrypting failed");
                    }
                    break;
                }

                case "history":
                    // display the history array as a list
                    displayHistory(history);
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
        // write to logger
        logWriter.println("EXIT Driver exited.");
        sc.close();
    }
}
