package com.example;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        // JSON String
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("name", "mkyong");
        jsonObject.put("age", 42);

        // JSON Array
        JsonArray list = new JsonArray();
        list.add("msg 1");
        list.add("msg 2");
        list.add("msg 3");

        jsonObject.put("messages", list);

        try (FileWriter fileWriter = new FileWriter("person.json")) {

            Jsoner.serialize(jsonObject, fileWriter);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The \"person.json\" file is written, check the root.");
    }
}
