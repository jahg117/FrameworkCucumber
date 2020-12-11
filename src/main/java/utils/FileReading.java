package utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;

public class FileReading {
    protected static String GlobalPath = Paths.get("").toAbsolutePath().toString() +
                            File.separator + "src"+ File.separator+ "main" + "" +
                            File.separator + "java" + File.separator + "config" + File.separator;

    private Logger logger = Logger.getLogger("");

    public String getField(String fieldName) {
        try {
            InputStream input = new FileInputStream(GlobalPath+"GlobalConfig.properties");
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(fieldName);
        }catch (FileNotFoundException e){
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public void loggerInfo(String message){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(GlobalPath+"log4j.properties"));
            PropertyConfigurator.configure(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info(message);
    }
}
