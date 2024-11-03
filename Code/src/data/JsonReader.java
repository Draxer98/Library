package data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonReader {

    /**
     * Reads a JSON file from the specified path and returns a JSONObject.
     *
     * @param path the path of the JSON file to read.
     * @return a JSONObject containing the data from the JSON file.
     * @throws IOException if an error occurs while reading the file.
     * @throws ParseException if the file is not in a valid JSON format.
     */
    public static JSONObject readObj(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(path)) {
            // Parse the JSON file and return it as a JSONObject
            return (JSONObject) parser.parse(reader);
        }
    }

    /**
     * Reads a JSON file from the specified path and returns a JSONArray.
     *
     * @param path the path of the JSON file to read.
     * @return a JSONArray containing the data from the JSON file.
     * @throws IOException if an error occurs while reading the file.
     * @throws ParseException if the file is not in a valid JSON format.
     */
    public static JSONArray readArray(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(path)) {
            // Parse the JSON file and return it as a JSONArray
            return (JSONArray) parser.parse(reader);
        }
    }
}