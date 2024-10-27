import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PropertiesFileTest {
    //https://www.baeldung.com/java-properties

    Properties appProps, catalogProps;

    {
        appProps = new Properties();
        try {
            appProps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        catalogProps = new Properties();
        try {
            catalogProps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("catalog.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void propertiesFile() throws IOException {
        // Example from https://www.baeldung.com/

//        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        String appConfigPath = rootPath + "app.properties";
//        String catalogConfigPath = rootPath + "catalog";
//
//        Properties appProps = new Properties();
//        appProps.load(new FileInputStream(appConfigPath));
//
//        Properties catalogProps = new Properties();
//        catalogProps.load(new FileInputStream(catalogConfigPath));

        // How I fix this problem

//        Properties appProps = new Properties();
//        appProps.load(new FileInputStream("src/main/resources/app.properties"));
//
//        Properties catalogProps = new Properties();
//        catalogProps.load(new FileInputStream("src/main/resources/catalog.properties"));

        /**
         * There is a problem with the file path:
         * When you use Thread.currentThread().getContextClassLoader().getResource(“”),
         * it returns the path to the root resource folder, but depending on the launch environment,
         * this may work differently. For example, in a compiled JAR file, the file paths may be different
         * than when running in an IDE.
         *
         * Wrong path for resources:
         * In your code, you are trying to load a file using a path generated from getResource(“”).getPath(),
         * but when you use this for classes that are inside src/main/resources, Java may not find the files
         * correctly because the resources inside the JAR may not have the exact file path.
         *
         * A better approach is to load resources:
         * Using getResourceAsStream() instead of a file path allows you to work correctly
         * both in the development environment and when running the compiled program (JAR).
         */
        Properties appProps = new Properties();
        appProps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties"));
        System.out.println("APP properties - " + appProps);

        Properties catalogProps = new Properties();
        catalogProps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("catalog.properties"));
        System.out.println("Catalog properties - " + catalogProps);

        String appVersion = appProps.getProperty("version");
        assertEquals("1.0", appVersion);

        assertEquals("files", catalogProps.getProperty("c1"));
    }

    @Test
    public void xmlFile() throws IOException {
//        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        String iconConfigPath = rootPath + "icons.xml";
//        Properties iconProps = new Properties();
//        iconProps.loadFromXML(new FileInputStream(iconConfigPath));

        Properties iconProps = new Properties();
        iconProps.loadFromXML(Thread.currentThread().getContextClassLoader().getResourceAsStream("icons.xml"));
        System.out.println("XML properties - " + iconProps);


        assertEquals("icon1.jpg", iconProps.getProperty("fileIcon"));
    }

    @Test
    public void getProperties() {
        String appVersion = appProps.getProperty("version");
        String appName = appProps.getProperty("name", "defaultName");
        String appGroup = appProps.getProperty("group", "baeldung");
        String appDownloadAddr = appProps.getProperty("downloadAddr");

        //ava.lang.ClassCastException: class java.lang.String cannot be cast to class java.lang.Float
//        float appVerFloat = (float) appProps.get("version");


        assertEquals("1.0", appVersion);
        assertEquals("TestApp", appName);
        assertEquals("baeldung", appGroup);
        assertNull(appDownloadAddr);
    }

    @Test
    public void setProperties() {
        appProps.setProperty("name", "NewAppName"); // update an old value
        appProps.setProperty("downloadAddr", "www.baeldung.com/downloads"); // add new key-value pair

        String newAppName = appProps.getProperty("name");
        assertEquals("NewAppName", newAppName);

        String newAppDownloadAddr = appProps.getProperty("downloadAddr");
        assertEquals("www.baeldung.com/downloads", newAppDownloadAddr);

        //Use only String
        //appProps.put("version", 2);
        appProps.put("version", "2");
        System.out.println(appProps.getProperty("version"));
    }

    @Test
    public void removeProperties() {
        String versionBeforeRemoval = appProps.getProperty("version");
        assertEquals("1.0", versionBeforeRemoval);

        appProps.remove("version");
        String versionAfterRemoval = appProps.getProperty("version");
        assertNull(versionAfterRemoval);
    }

    @Test
    public void storeToPropertiesFiles() throws IOException {
        //String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String newAppConfigPropertiesFile = "src/main/resources/" + "newApp.properties";
        appProps.store(new FileWriter(newAppConfigPropertiesFile), "store to properties file");
        appProps.storeToXML(new FileOutputStream("src/main/resources/" + "newApp.xml"), "store to xml file");
    }

    @Test
    public void otherCommonOperations() {
        appProps.list(System.out); // list all key-value pairs
        System.out.println("-----------------------------------------------");
        Enumeration<Object> valueEnumeration = appProps.elements();
        while (valueEnumeration.hasMoreElements()) {
            System.out.println(valueEnumeration.nextElement());
        }

        Enumeration<Object> keyEnumeration = appProps.keys();
        while (keyEnumeration.hasMoreElements()) {
            System.out.println(keyEnumeration.nextElement());
        }

        int size = appProps.size();
        assertEquals(3, size);
    }

}
