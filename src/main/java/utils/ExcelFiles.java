package utils;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

import java.io.File;
import java.nio.file.Paths;

public class ExcelFiles {
    public static String GlobalPath = Paths.get("").toAbsolutePath().toString() +
            File.separator + "src" + File.separator + "main" + "" +
            File.separator + "java" + File.separator + "config" + File.separator
            + "excel" + File.separator;

    public void savePEPId(String pepID) throws FilloException {
        Fillo fillo=new Fillo();
        Connection connection=fillo.getConnection(GlobalPath+"patientList.xlsx");
        String strQuery="INSERT INTO pepID(PatientID) VALUES('"+pepID+"')";
        connection.executeUpdate(strQuery);
        connection.close();
    }
}
