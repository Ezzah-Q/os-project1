//import java libraries
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public static void main(String[] args) {
        // read and save command line argument
        String file = args[0];
        // create and set datetime pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // open logfile
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true));
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            //loop
            String line;
            while ((line = reader.readLine()) != null) {
                if ("QUIT".equals(line)) break;

                String[] parts = line.split(" ", 2);
                // action = word before first space
                String action = parts[0];
                String message;
                //message = rest of line after first space
                if (parts.length > 1) {
                    message = parts[1];
                } else {
                    message = "";
                }

                String time = LocalDateTime.now().format(formatter);
                // write to log file in <datetime> [action] [message] format
                writer.println(time + " [" + action + "] " + message);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}