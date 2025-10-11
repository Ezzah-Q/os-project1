// encyrption program pseudo code

// forever loop
    // read line from standardinput
    // split input line into command and argument

    // if command was PASS
        // Output RESULT password set to driver

    // else if command was encyprt
        // call encrypt vignere function with passkey and string as argument, put result in string
        // Output RESULT string to driver

    // else if command was decrypt
        // call decrypt vignere function with passkey and string as argument, put result in string
        // output RESULT string to driver

    // else if command was quit
        // exit the program

// function definition vignere encrypt (string, passkey)
    // define an empty string to store encypted word
    // loop from i = 0 to length of string to encrypt
        // store letter i from string into letter
        // store letter i from passkey into key letter
        // find the amount we have to shift (A - key letter)
        // shift the letter and get the new letter
        // store new letter in the string
    // return encrypted word

// function definition vignere decrypt (string, passkey)
    // define an empty string to store decypted word
    // loop from i = 0 to length of string to decrypt
        // store letter i from string into letter
        // store letter i from passkey into key letter
        // find the amount we have to shift (A - key letter)
        // shift the letter backward and get the new letter
        // store new letter in the string
    // return encrypted word

