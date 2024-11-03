package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * The class, depending on the arguments “json”  or “xml” passed,
 * saves the file of the selected format in the folder "target/".
 * If the format parameter is not passed, json will be the default.
 * Example file:
 * { “message”: “Hello <text from external properties file, username=your name> !"}
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.warn("Start program------------------------------>");

        String username = getProperty("username");
        logger.info("set username: " + username);

        Message message = new Message("Hello " + username + " !");
        logger.info("new Message object: " + message);

        String format = args.length == 0 ? "" : args[0].toLowerCase().trim();
        logger.info("args parameter: " + format);

        switch (format) {
            case "json": {
                saveJson(message);
                logger.info("switch json parameter");
                break;
            }
            case "xml": {
                saveXml(message);
                logger.info("switch xml parameter");
                break;
            }
            default: {
                saveJson(message);
                logger.info("switch default parameter");
            }
            logger.warn("<------------------------------End program");
        }
    }

    /**
     * Finds the .properties file and sets the requested parameter.
     * @param name parameter name
     * @return parameter value
     */
    private static String getProperty(String name) {
        Properties appProps = new Properties();

        try {
            appProps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("message.properties"));
        } catch (IOException e) {
            logger.warn("Property doesn't find");
            logger.error("Property Exception");
            logger.trace(e);
            throw new RuntimeException(e);
        }
        logger.info("Property is find");
        return appProps.getProperty(name);
    }

    /**
     * Saves the created object in json format
     * @param obj a class object to write to a file
     */
    private static void saveJson(Message obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("target/Message.json"), obj);
        } catch (IOException e) {
            logger.warn("Json doesn't save");
            logger.error("Json Exception");
            logger.trace(e);
            throw new RuntimeException(e);
        }
        logger.info("Json file is saved");
    }

    /**
     * Saves the created object in xml format
     * @param obj a class object to write to a file
     */
    private static void saveXml(Message obj) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(new File("target/Message.xml"), obj);
        } catch (IOException e) {
            logger.warn("Xml doesn't save");
            logger.error("Xml file Exception");
            logger.trace(e);
            throw new RuntimeException(e);
        }
        logger.info("Xml file is saved");
    }
}