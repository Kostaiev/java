The program will create a json or xml file with the name from the properties file

Parametr args: xml or json (The default is json)

Path to the file with parameters: src/main/resources/message.properties

To create a jar file, run the following commands:
1.Build the project with a maven
mvn package

2.Run the jar file with libraries
java -jar target/original-json-1.0-SNAPSHOT.jar

3.To rebuild the project
mvn clean package

