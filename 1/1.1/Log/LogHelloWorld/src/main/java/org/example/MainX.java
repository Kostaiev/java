package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainX {
    private static final Logger logger = LogManager.getLogger(MainX.class);

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            logger.info("This is log message number: " + i);
        }

        logger.error("This is an error message.");
        logger.warn("This is a warning message.");
    }
}
