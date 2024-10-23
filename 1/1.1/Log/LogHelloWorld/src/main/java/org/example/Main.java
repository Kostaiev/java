package org.example;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        BasicConfigurator.configure();
        logger.info("Info1");
        logger.error("Error 1");
        logger.debug("Debug 1");
        for(int i = 0; i < 2000; i++) {
            logger.info("This is the " + i + " time I say 'Hello World'.");
           //Thread.sleep(100);
        }
    }
}