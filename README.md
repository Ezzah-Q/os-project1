CS 4348 Project 1
Multi-Process Vignere Encryption Program

Author: Ezzah Qureshi
Date: October 19th 2025
Language: Java

Files:

- Driver.java
- Encryption.java
- Logger.java
- devlog.md
- README.md

Overview:
There are three seperate Java programs that communicate with each other using pipes. This program allows the user to set a passwords and encrypt or decrypt strings via vignere cipher. All commands are logged out to a text file.

1. Logger.java

   - this file accepts a text file argument from Driver.java and communicates with it through pipes.
   - the driver program sends messages to the logger program which the logger then logs to a text file
   - logger logs start and exit of driver program as well as the start and end of each command

2. Encryption.java

   - this file handles encyption/decryption of strings that the user chooses, as well setting a password
   - the user cannot encrypt or decrypt a string before setting a password, which is handled in thsi program
   - this program accepts commands from the driver
     - PASS <password> - sets password
     - ENCRYPT <plain text> - encrypts word using password
     - DECRYPT <cipher text> - decrypts word using password
       - decrypts to the same word only if you used the same passowrd when encrypting
     - QUIT - exit program
   - this program responds back with
     - RESULT <message> - success message
     - ERROR <message> - failed message

3. Driver.java
   - main program that sets up processes and talks to the other two programs
   - provides menu for user to choose from
   - allows user to choose a new string or one from history
   - validates string and records it in history
     - passwords are not recorded in history nor are they logged
   - handles encryption response and sends appropriate messages to the logger and standard output

How to compile and run

- I uploaded the zipped file containing the three java programs
- from the project directory enter this to compile the java program:
  - javac Logger.java Encryption.java Driver.java
- to run program enter this:

  - java Driver test_log.txt

- program has been run on CS1 server, so it should work when you test it
- all sessions have been recording in the devlog.md and .git should be in the zipped file as well
