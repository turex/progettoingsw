package main;

import java.io.FileWriter;
import java.io.IOException;
import com.github.cliftonlabs.json_simple.JsonObject;

public class JsonHelper {

	JsonHelper(){
    JsonObject customer = new JsonObject();
    

}
	
	void writetest() {
		 //Creating a JSONObject object
	      JsonObject jsonObject = new JsonObject();
	      //Inserting key-value pairs into the json object
	      jsonObject.put("ID", "1");
	      jsonObject.put("First_Name", "Shikhar");
	      jsonObject.put("Last_Name", "Dhawan");
	      jsonObject.put("Date_Of_Birth", "1981-12-05");
	      jsonObject.put("Place_Of_Birth", "Delhi");
	      jsonObject.put("Country", "India");
	      try {
	         FileWriter file = new FileWriter("C:\\Users\\Salvo\\git\\progettoingsw\\export jar\\output.json");
	         file.write(jsonObject.toString());
	         file.close();
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      System.out.println("JSON file created: "+jsonObject);
	   }
		
	}



