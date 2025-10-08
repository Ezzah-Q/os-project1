# Dev Log – CS4348 Project 1

10-01-2025, 10:26 pm

- created the project local repo and connected it to GitHub
- added the java files (logger, driver, encrypter, readme, and devlog)
- looked at project description, thoughts for now...
  - seems like I should start on some psuedocode first
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

- for this session I plan on creating psuedo code for logger program. I am not going to go into detail, just an overall layout. If I have time, I will move on to requirements and psuedocode for eeither ncryption program or driver program

10-2-2025, 1:54 pm

- for today's session I wrote a rough psuedo code for the logger program. I probably need to add error handling. 
- I did not get the change to look at encryption or driver programs, will look into that in the next session
- forgot to mention in first devlog entry, but I am writing program in java

10-8-2025, 12:10 pm
- this is before session, there aren't any new developments
- this session I plan to write the psuedo code for driver and encryption program
- once i have psuedo code or a rough idea of the structure of the program I'll begin writing actual code.
