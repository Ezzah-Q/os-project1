// driver program, psuedo code

// accept single command line argument, name of log file

// create two new processes (one for logger and one for encryption)
    // start Logger subprocess with the filename as the argument
        // connect standard input (one way communication to send messages)
    // start encryption subprocess
        // connect standard unput and output (back and forth communciation)

// start driver program --> send this to logger

// create an array to hold the history

// loop:
    // print menu
        // 1. password - Set a password
        // 2. encrypt - Encrypt a string
        // 3. decrypt - Decrypt a string
        // 4. history - View history
        // 5. quit - Quit program
    // prompt user to enter a command
    // read command
    // send command to logger

    // if user chooses password
        // prompt user to either use history string or enter new one
            // if user chooses history
                // display the history array as a list
                // prompt user to choose one using the index
                // set the chosen word as password
            // else (user chooses to enter new one)
                // prompt user to enter password
                // read the password
                // set password as current encryption key
            // send password to encryption program "PASS" + password
            // read response from encryption program
                // if encyption program responds back with RESULT
                    // tell user that the password was set successfully
                    // send the encryption response to logger
                // else (ERROR)
                    // tell user that the password was not set successfully
                    // send the encryption response to logger

    // else if command was encrypt
        // prompt user with options of using string in history or new string
            // if user chooses history
                // display history array as list
                // prompt user to choose one using the index
                //
            // else (user chooses new string)
                // propmt user to enter string
                // record it in history
                // read the string
            // send encypt message to encryption program
            // read response
            // if response starts with RESULT
                // print out result
                // add encypted string to history
                // send result to logger
            // else (error)
                // print error message
                // send result to logger

    // else if command was decrypt
        // prompt user with option of using string in history or new string
            // if user chooses history
                // display history array as list
                // prompt user to choose one using the index
            // else (chooses new string)
                // prompt user to enter string
                // record it in history
                // read it
            // decrypt message to encryption program
            // read response
            // if response starts with RESULT
                // print result
                // save in history
                // send result to logger
            // else (error
                // print error message
                // send result to logger

    // else if command was history
        // loop from i = 0 to length of array
            // print out array
        // send history request and result to log

    // else if command was quit
        // send to logger quit
        // send to encyprtion quit
        // break loop

// end loop

// end program
