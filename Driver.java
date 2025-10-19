// Driver program
// the main program that interacts with user and communicates
// with encryption and logger programs though processes via pipes
import java.io.*;
import java.util.*;
public class Driver {

    /**
     * This function displays the history whenever the user decides
     * to choose a string from history or type in history command
     * @param history String from ArrayList
     */
    private static void displayHistory(ArrayList<String> history) {
        System.out.println("          History ");
        System.out.println("------------------------------");
        // loop over history and print each word
        for (int i = 0; i < history.size(); i++) {
            System.out.println((i + 1) + ") " + history.get(i));
        }
        System.out.println("------------------------------");
    }

    /**
     * Boolean function called to determine if a string entered is valid
     * checks for chars besides letters and emptiness
     * @param input String of user input
     * @return Bool signaling if word is valid or not
     */
    private static boolean isWordValid(String input) {
        // if input is empty string return false
        if(input.trim().isEmpty()) {
            return false;
        }
        // if input has characters other than letters, return false
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(!Character.isLetter(c)){
                return false;
            }
        }
        return true;
    }

    /**
     * this function handles the responses from the Encryption program, depending
     * on if the response was an error or successful result
     * @param result String from encryption program
     * @param logWriter PrintWriter writing to log
     * @param history String from array list
     * @param commandType String signaling the command set by user
     */
    private static void handleEncryptionResponse(String result, PrintWriter logWriter, ArrayList<String> history, String commandType) {
        // if the encryption response starts with RESULT print out the success messages to standard output and logger
        if (result != null && result.startsWith("RESULT")){
            // remove the "RESULT" part of the output so it's not logged and print messages
            String output = result.substring(7).trim();
            System.out.println(commandType + " successful: " + output);
            // Add word to history if it is not a password
            if (!commandType.equalsIgnoreCase("PASS")) {
                history.add(output);
            }
            logWriter.println(commandType + " RESULT " + output);
            System.out.println();

        // if the encryption response starts with ERROR print out the error messages to standard output and logger
        } else if (result != null && result.startsWith("ERROR")) {
            // remove the "RESULT" part of the output so it's not logged and print messages
            String errorMsg = result.substring(6).trim();
            System.out.println(commandType + " not successful: " + errorMsg);
            logWriter.println(commandType + " ERROR " + errorMsg);
            System.out.println();

        // if the encryption response does not start with either, or is empty, then take note of it
        } else {
            System.out.println("Unexpected or no response from encryption program: " + result);
            logWriter.println(commandType + " ERROR Unexpected or no response: " + result);
        }
    }

    /**
     * this function runs the program logic for choosing a string
     * a user may choose a string from history or type in a string.
     * if a user chooses history, they are allowed to switch their minds and type in a string
     * @param sc Scanner to read from user input
     * @param history String from array list
     * @param prompt String that asks user to choose history or new string
     * @return string that user chooses
     */
    private static String getStringFromHistoryOrNew(Scanner sc, ArrayList<String> history, String prompt) {
        while (true) {
            // print out the prompt and record user input
            System.out.print(prompt + " (Y/N): ");
            String choice = sc.nextLine().trim();

            // if input is y or Y
            if (choice.equalsIgnoreCase("y")) {
                // message user if history is empty
                if (history.isEmpty()) {
                    System.out.println("History is empty. Please choose no.");
                    continue;
                }

                // if history is not empty then display it
                displayHistory(history);

                // continuously repeat this prompt until program receives proper input
                while(true) {
                    // if after seeing history the user changes their mind they can instead put in a new string
                    System.out.println("Type in history index to choose word or type 'c' to enter a string instead.");
                    System.out.print("Your choice: ");
                    String input = sc.nextLine().trim();

                    // If user puts in c or C they can enter a new string
                    if (input.equalsIgnoreCase("C")) {
                        // continuously repeat this prompt until program receives proper input
                        while (true) {
                            System.out.print("Enter a string: ");
                            String newInput = sc.nextLine().trim();

                            // check if entered word is valid, return if it is and repeat if not
                            if (isWordValid(newInput)) {
                                return newInput.toUpperCase();
                            } else {
                                System.out.println("Invalid string. Only letters (no blanks or punctuation). Please try again.");
                            }
                        }
                    // if user decides to use history
                    } else {
                        try {
                            // if user enters an appropriate index number from history, return that word
                            int index = Integer.parseInt(input) - 1;
                            if (index >= 0 && index < history.size()) {
                                return history.get(index).toUpperCase();
                            } else {
                                // is user picks a number not in the history it will be asked to pick again
                                System.out.println("Invalid number. Choose a number from history index.");
                            }
                            // if the user does not enter a number the program will throw an error
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number or 'C'.");
                        }
                    }
                }
            // if user puts in n or N then go straight to entering in new string
            } else if (choice.equalsIgnoreCase("n")) {
                while (true) {
                    System.out.print("Enter a string: ");
                    String newInput = sc.nextLine().trim();

                    // check if word is valid
                    if (isWordValid(newInput)) {
                        return newInput.toUpperCase();
                    } else {
                        System.out.println("Invalid string. Only letters (no blanks or punctuation). Please try again.");
                    }
                }
            // if user does not type in y or n then ask user to type in y or n
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
        // continuously loop through the menu until user types in quit
        while(true) {
            System.out.println("           Menu");
            System.out.println("------------------------------");
            System.out.println("1. Password - set password");
            System.out.println("2. Encrypt - encrypt word");
            System.out.println("3. Decrypt - decrypt word");
            System.out.println("4. History - show history");
            System.out.println("5. Quit - stop program");
            System.out.println("------------------------------");

            // prompt user and take their input
            System.out.print("Please Enter a Command: ");
            String command = sc.nextLine().trim().toLowerCase();
            System.out.println();

            // switch statement to consider 4 possible cases
            switch (command) {
                case "password": {
                    // call function for user to choose a string to set as password
                    String password = getStringFromHistoryOrNew(sc, history, "Would you like to choose password from history?");
                    // write to logger and encryption program
                    logWriter.println("SET_PASSWORD Setting password.");
                    cryptWriter.println("PASS " + password);
                    String result = cryptReader.readLine();
                    // function to handle encryption response and send appropriate messages
                    handleEncryptionResponse(result, logWriter, history, "PASS");
                    break;
                }

                case "encrypt": {
                    // call function for user to choose a string to encrypt
                    String encrypt = getStringFromHistoryOrNew(sc, history, "Would you like to choose a string from history?");
                    // write to logger and encryption program
                    logWriter.println("ENCRYPT " + encrypt);
                    cryptWriter.println("ENCRYPT " + encrypt);
                    // only add word to history if not already in history
                    if (!history.contains(encrypt)) {
                        history.add(encrypt);
                    }
                    // read result from encryption program
                    String result = cryptReader.readLine();
                    handleEncryptionResponse(result, logWriter, history, "ENCRYPT");
                    break;
                }

                case "decrypt": {
                    // call function for user to choose a string to decrypt
                    String decrypt = getStringFromHistoryOrNew(sc, history, "Would you like to choose a string from history?");
                    // write to logger and encryption program
                    logWriter.println("DECRYPT " + decrypt);
                    cryptWriter.println("DECRYPT " + decrypt);
                    // only add word to history if not already in history
                    if (!history.contains(decrypt)) {
                        history.add(decrypt);
                    }
                    // read result from encryption program
                    String result = cryptReader.readLine();
                    handleEncryptionResponse(result, logWriter, history, "DECRYPT");
                    break;
                }

                case "history":
                    // display the history array as a list and record in log
                    displayHistory(history);
                    logWriter.println("HISTORY Viewed history");
                    break;

                case "quit":
                    // write to logger and encryption program
                    logWriter.println("EXIT Driver exited.");
                    logWriter.println("QUIT");
                    cryptWriter.println("QUIT");

                    // wait for processes to end
                    loggerProcess.waitFor();
                    encryptProcess.waitFor();

                    break label;

                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
        sc.close();
    }
}