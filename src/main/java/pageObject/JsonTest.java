package pageObject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.Iterator;

public class JsonTest {
    public static String GlobalPath = Paths.get("").toAbsolutePath().toString() +
            File.separator + "src"+ File.separator+ "main" + "" +
            File.separator + "java" + File.separator + "config" + File.separator
            + "json" + File.separator;
    public static void main(String []args) {

        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader(GlobalPath+"test.json"));

            JSONObject jsonObject = (JSONObject) obj;

            String nombre = (String) jsonObject.get("nombre");
            System.out.println("nombre:"+nombre);

            long edad = (Long) jsonObject.get("edad");
            System.out.println("edad:"+edad);

            // recorrer arreglo
            JSONArray leng= (JSONArray) jsonObject.get("lenguajes_favoritos");
            System.out.println("lenguajes_favoritos:");
            Iterator iterator =leng.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

        }catch(Exception ex){
            System.err.println("Error: "+ex.toString());
        }


        JSONObject obj = new JSONObject();
		obj.put("nombre", "Ferenando");
		obj.put("edad", new Integer(31));

		JSONArray list = new JSONArray();
		list.add("Javaa");
		list.add("Ceyloon");
		list.add("Pythonn");

		obj.put("lenguajes_favoritos", list);

		try(FileWriter file = new FileWriter(GlobalPath+"test.json")){

			file.write(obj.toJSONString());
			file.flush();



		}catch(Exception ex){
			System.out.println("Error: "+ex.toString());
		}
		finally{
			System.out.print(obj);
		}
    }
}
