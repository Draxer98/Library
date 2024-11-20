package data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This class write json objects json arrays in file.
 */
public class JsonWriter {

    /**
     * Writes a JSON object to the specified file.
     *
     * @param obj  the JSON object to write.
     * @param path the path of the file where the JSON object will be written.
     * @throws RuntimeException if an error occurs while writing to the file.
     */
    public static void writeObj(JSONObject obj, String path) {
        try (FileWriter file = new FileWriter(path)) {
            file.write(obj.toJSONString()); // Convert the JSON object to a string and write it to the file
            file.flush(); // Ensure all data is written
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while writing the JSON object to the file: " + path, e);
        }
    }

    /**
     * Writes a JSON array to the specified file.
     *
     * @param arr  the JSON array to write.
     * @param path the path of the file where the JSON array will be written.
     * @throws RuntimeException if an error occurs while writing to the file.
     */
    public static void writeList(JSONArray arr, String path) {
        try (FileWriter file = new FileWriter(path)) {
            file.write(arr.toJSONString()); // Convert the JSON array to a string and write it to the file
            file.flush(); // Ensure all data is written
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while writing the JSON array to the file: " + path, e);
        }
    }
}
