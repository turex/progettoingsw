package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

//Uso il Singleton e il wrapper per creare piu JSon (Medico, paziente)

public class JsonHelper {
	
	private static JsonHelper istance; //Singleton istance
	private static JSONArray medico = new JSONArray(); //inserisco qui l'array del JSON per i medici
	private static JSONArray paziente = new JSONArray(); //inserisco qui l'array del JSON per i medici
	

	private JsonHelper() {
	         
	    }
	
	/*
	 * 
	 * Paramatri :
	 * 
	 * @obj : componenti del JSON (esempio : nome paziente ecc..)
	 * @typeobj : inserisco nell'array se é medico o paziente
	 * @employeeList : contiene il database 
	 * @typeofdb : parametrizzo il database, qui viene inserito se é medico o paziente
	 */
	
	 void addtoJson(String firstname, String lastname, String typeofdb) {
		JSONObject obj_med = new JSONObject();
		JSONObject typeobj_med = new JSONObject();
		
		JSONObject obj_paz = new JSONObject();
		JSONObject typeobj_paz = new JSONObject();
		
		
		switch(typeofdb) {
		
		case "Medico":
			obj_med.put("firstName", firstname);
			obj_med.put("lastName", lastname);
			typeobj_med.put("Medico", obj_med);
			medico.add(typeobj_med);
			
			
		case "Paziente":
			typeobj_paz.put("Paziente", obj_paz);
			paziente.add(typeobj_paz);
		}
		
	}
	/*
	 * 
	 * Parametri :
	 * 
	 * @typeofdb: semplifichiamo la gestione suddividendo i daatabase
	 */
	 void readJson(String typeofdb) {
		JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader(typeofdb + ".json"))
        {
            //Read JSON file
            try {
				Object obj = jsonParser.parse(reader);
			} catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
            //Iterate over employee array
            medico.forEach( emp -> parseEmployeeObject( (JSONObject) emp, typeofdb ) );
            
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	void writeJson(String typeofdb) {
		
		//Write JSON file
        try (FileWriter file = new FileWriter(typeofdb + ".json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(medico.toJSONString()); 
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	
	}
	
    private static void parseEmployeeObject(JSONObject database, String typeobj) 
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) database.get(typeobj);
         
        //Get employee first name
        String firstName = (String) employeeObject.get("firstName");    
        System.out.println(firstName);
         
        //Get employee last name
        String lastName = (String) employeeObject.get("lastName");  
        System.out.println(lastName);
         
    }
		

	
	public static JsonHelper getIstance() {
		if(istance == null) {
			istance = new JsonHelper();
		}
		
		return istance;
	}
	
	
	private void writeMedico() {
		
		
	}
	

	String cleanString(String[] split, int i) {
		
		String clean = "";
		if (split[i].contains("["))
		clean = split[i].replace("[", "");
		else if (split[i].contains("]"))
			clean = split[i].replace("]", "");
		else if (split[i].contains(","))
			clean = split[i].replace(",", "");
		else 
			clean = split[i];
		
		return clean;
		
	}

}




