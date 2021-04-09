package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;

public class JsonFiles {
    public static String GlobalPath = Paths.get("").toAbsolutePath().toString() +
            File.separator + "src" + File.separator + "main" + "" +
            File.separator + "java" + File.separator + "config" + File.separator
            + "json" + File.separator;

    private String fileName = "";

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getField(String fieldName) throws NoSuchFileException {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(GlobalPath + "" + fileName + ".json"));
            JSONObject jsonObject = (JSONObject) obj;
            return (String) jsonObject.get(fieldName);
        } catch (Exception e) {
            throw new NoSuchFileException("The file was not found or the field is an invalid type");
        }
    }

    public JSONArray getFieldArray(String fieldName) throws NoSuchFileException {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(GlobalPath + "" + fileName + ".json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObject.get(fieldName);
            return jsonArray;
        } catch (Exception e) {
            throw new NoSuchFileException("The file was not found or the field is an invalid type");
        }
    }

    public String getRandomFieldArray(String fieldName) throws NoSuchFileException {
        JSONParser parser = new JSONParser();
        Random random = new Random();
        try {
            Object obj = parser.parse(new FileReader(GlobalPath + "" + fileName + ".json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObject.get(fieldName);
            return (String) jsonArray.get(random.nextInt(jsonArray.size()));
        } catch (Exception e) {
            throw new NoSuchFileException("The file was not found or the field is an invalid type");
        }
    }

    public void storeDataIntoJSON(HashMap<String, String> dataInformation) throws NoSuchFileException {
        JSONObject obj = new JSONObject();
        int dataCounter = 0;
        try (FileWriter file = new FileWriter(GlobalPath + fileName + ".json")) {
            for (String key : dataInformation.keySet()) {
                obj.put(key, dataInformation.get(key));
            }
            file.write(obj.toJSONString());
            file.flush();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            System.out.print(obj);
        }
    }
}
