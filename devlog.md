# Dev Log – CS4348 Project 1

10-01-2025, 10:26 pm

- created the project local repo and connected it to GitHub
- added the java files (logger, driver, encrypter, readme, and dev log)
- looked at project description, thoughts for now...
  - seems like I should start on some pseudocode first
  - focus on logger file as that seems the easiest
  - so logger needs the log file as input from driver, since it write to it
  - seems like the [action] part of a line is coming from the driver
  - we also need DateTime format but thats just use of library
  - also need classes for file reading and writing (FileWriter, BufferedReader)
  - Driver communicating with encryption and logger through standard input/output
  - will start on pseudocode maybe coding in next working session

10-2-2025, 10:58 am

- this is before session
- after reading the logger requirements again, I can think of main things needed for logger program

1. program needs to read logfile argument given from driver
2. open the log file
3. read the lines form standard input from driver in a loop, with parsing the line to get action and message
4. write to file in the format wanted
5. close file

- for this session I plan on creating pseudocode for logger program. I am not going to go into detail, just an overall layout. If I have time, I will move on to requirements and psuedocode for eeither ncryption program or driver program

10-2-2025, 1:54 pm

- for today's session I wrote a rough pseudocode for the logger program. I probably need to add error handling. 
- I did not get the change to look at encryption or driver programs, will look into that in the next session
- forgot to mention in first dev log entry, but I am writing program in java

10-8-2025, 12:10 pm
- this is before session, there aren't any new developments
- this session I plan to write the pseudocode for driver and encryption program
- once i have pseudocode or a rough idea of the structure of the program I'll begin writing actual code.

10-8-2025, 3:10 pm
- for today's session I finished up writing pseudocode based on project pdf
- this made me more confident with the code structure, I know the program flow of execution and the requirements needed for each program
- also now the actual coding part is easier
- for next session I will start coding the logger program based on the pseudocode I wrote
- it might be helpful if I write a little bit of the driver program (part where it sends logs to logger) to see if processes and pipes were properly created

10-10-2025, 6:59 pm
- before session, there aren't new developments
- this session, I plan to start writing actual code, starting off with the logger program
- I need to import the date time library and use try catch block
- If I am able to get through logger code this session then I'll write part of driver and test the logger to driver communication pipe to see if its writing to file properly

10-10-2025 11:42 pm
- For today's session I finished first rough code for logger program
- Although it wasn't really clear in instructions, I assumed that driver is sending logger commands in form of [action] [message]
- in the example process code professor salazar provided for process creation, he used an older process api Runtime.runtime.exec
  - there is a newer one ProcessBuilder which configures I/O channels easily, so I used that for process creation in driver
- I tried to test logger and driver communication but got errors regarding file creation
  - just decided to test logger file text output, which works as expected
- Finished coding driver program, hardest part was creating the first case (password), but then its just the same code template for the rest of them
- main confusion is in what exactly i record to logger. 
  - Instructions say command and result, so I assume that the [action] part is the command and the [message] is the result
- I accomplished goal for this session got major chunk of code done
- next session will write encryption code, test it to see if it works, and make sure all classes are integrated properly