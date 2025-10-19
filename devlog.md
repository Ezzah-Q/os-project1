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

10-11-2025, 1:52 pm
- this is before session
- plan for today is to write the encryption program with a few things in mind (in my pseudocode)
  - to implement vignere encryption and decryption of a word i can loop through the passkey and string 
  - find the numerical value of the letter in the passkey and shift the string in letter by it accordingly, making sure to do mod 26
  - to decrypt i can just subtract the shift
- encryption and decryption will be functions called from main
- after writing the encryption program i will test it separately to see if the encryption works

10-11-2025 
- so far writing encryption program isn't too bad, it is similar to logger code
- just used same code logic for reading line and extracting action and message from logger
- for encryption function used Stringbuilder to concatenate letter to string, more efficient that using +
- also made sure to repeat the key word being used to encrypt if it's shorter than word to encrypt --> gave errors
- also having trouble with the project structure, project can't seem to find my encryption program file (not in proper folder)
  - just a refactoring issue, quickly resolved
- tested the encryption program and encryption works, encryption and decryption lead to same word
- realized that I need to add a statement that tells user the history is empty if they choose to use history on their first go
  - spent long time fixing the logic flow of if command equals blank code block in driver.java
- logger and encryption pipes were not working, but that was because I wrote the wrong file name in process creation step in driver
- realized that logger logs the start of the command (when user chooses that command) and result
  - i've been doing just result, will just add a write to logger line when command is first chosen
- code was beginning to be really long, so i looked at prof Salazar's example he showed us and condensed the display 
- realized I could functionalize a lot of the code...
  - decided to write a display history function and a function that asks if the user wants to get form history or not
- tested whole program, works as expected, logging works, encryption works, just need to fix little bugs and add error checking 
- overall, this session I wrote and tested encryption code, and started fixing little errors, created functions etc
- next session i plan to write a code block that allows user to exit history and enter a new string

10-18-2025 11:30 am
- before session
- not much has changed in between sessions, finished all the coding
- this sesssion i plan to clean up everything, add error checking, add a code block that allow user to exit history and enter a new string

10-18-2025 11:50 pm
- after session, many changes today
- implemented code block to take user form history to typing in a new string
- also made sure that the user cannot encrypt/decrypt without a password put in place
- added error checking to driver, making sure the entered string is only letter etc
- for handling the encryption repsonses in driver, i just created that as a seperate function to increase readability
- took care of a couple of logging errors, such as the driver exit message not showing up
  - i had that line outside of the 'quit' case statement block
- basically finished program, all of works as expected after thorough testing 
- added comments
- need to make sure program runs on the utd servers, which i will do next session.

10-18-2025 12:27 am
- before session
- had prev session right before this, but i tried to run this program on cs1 servers but cs1 servers have an older version of java
  - i need to replace some keywords i used in my program with actual manual code

10-18-2025 12:52 am
- after session
- fixed the repeating function
- tested it on cs1 server and it works and functions as intended
- will create readme and turn in

10-19-2025 10:45 am
- before session
- need to write readme.md and clean up repo to turn in

10-19-2025 10:51 am
- created readme
- deleted the .class files
- ready to turn in