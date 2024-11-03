package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * “If a client can reasonably be expected to recover from an exception, make it a checked exception.
 * If a client cannot do anything to recover from the exception, make it an unchecked exception.”
 */
public class Main {

    public static void main(String[] args) {
        // Checked Exception
        // Therefore, we should use the throws keyword to declare a checked exception
        try {
            checkedExceptionWithThrows();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Unchecked Exceptions
        divideByZero();
        nullOrEmptyException();

        /**
         * For example, before we open a file, we can first validate the input file name.
         * If the user input file name is invalid, we can throw a custom checked exception:
         *
         * if (!isCorrectFileName(fileName)) {
         *     throw new IncorrectFileNameException("Incorrect filename : " + fileName );
         * }
         * Copy
         * In this way, we can recover the system by accepting another user input file name.
         *
         * However, if the input file name is a null pointer or it is an empty string,
         * it means that we have some errors in the code. In this case,
         * we should throw an unchecked exception:
         *
         * if (fileName == null || fileName.isEmpty())  {
         *     throw new NullOrEmptyException("The filename is null or empty.");
         * }
         */
    }

    // Checked Exception
    private static void checkedExceptionWithThrows() throws FileNotFoundException {
        File file = new File("not_existing_file.txt");
        FileInputStream stream = new FileInputStream(file);
    }

    private static void customCheckedException() throws IncorrectFileNameException {
        throw new IncorrectFileNameException("Name file");
    }

    //Unchecked Exceptions
    private static void divideByZero() {
        int numerator = 1;
        int denominator = 0;
        int result = numerator / denominator;
    }

    private static void nullOrEmptyException() {
        throw new NullOrEmptyException("Something went wrong");
    }
}


// Checked Exception

/**
 * The Exception class is the superclass of checked exceptions,
 * so we can create a custom checked exception by extending Exception
 */
class IncorrectFileNameException extends Exception {
    public IncorrectFileNameException(String errorMessage) {
        super(errorMessage);
    }
}

//Unchecked Exceptions

/**
 * The RuntimeException class is the superclass of all unchecked exceptions,
 * so we can create a custom unchecked exception by extending RuntimeException:
 */
class NullOrEmptyException extends RuntimeException {
    public NullOrEmptyException(String errorMessage) {
        super(errorMessage);
    }
}