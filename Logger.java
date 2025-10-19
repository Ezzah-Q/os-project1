// logger program
// writes to a text file given and read messages from by driver program
// logs to file in specific format
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
            // loop through line as long as there is a line
            String line;
            while ((line = reader.readLine()) != null) {
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
                // write to log file in <datetime> [action] [message] format
                String time = LocalDateTime.now().format(formatter);
                writer.println(time + " [" + action + "] " + message);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}