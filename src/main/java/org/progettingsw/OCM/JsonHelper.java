package org.progettingsw.OCM;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//Uso il Singleton e il wrapper per creare piu JSon (Medico, paziente)

public class JsonHelper {
	
	private static JsonHelper istance; //Singleton istance
	private static JSONArray medico = new JSONArray(); //inserisco qui l'array del JSON per i medici
	private static JSONArray paziente = new JSONArray(); //inserisco qui l'array del JSON per i medici
	
	public static ArrayList<String> np = new ArrayList<String>();

	private JsonHelper() {
	         
	    }
	
	/*
	 * 
	 * Paramatri :
	 * 
	 * @obj : componenti del JSON (esempio : nome paziente ecc..)
	 * @typeofdb : inserisco nell'array se é medico o paziente
	 * @employeeList : contiene il database 
	 * @typeofdb : parametrizzo il database, qui viene inserito se é medico o paziente
	 */
	
	 @SuppressWarnings("unchecked")
	void addtoJson(String nome, String cognome, String id, String professione,String nascita, String sesso,
					String prenotazione, String typeofdb) {
		 
		JSONObject obj_med = new JSONObject();
		JSONObject typeofdb_med = new JSONObject();
		
		JSONObject obj_paz = new JSONObject();
		JSONObject typeofdb_paz = new JSONObject();
		
		
		switch(typeofdb) {
		
		case "Medico":
			obj_med.put("nome", nome);
			obj_med.put("cognome", cognome);
			obj_med.put("id", id);
			obj_med.put("professione", professione);
			typeofdb_med.put("Medico", obj_med);
			medico.add(typeofdb_med);
			break;
			
			
		case "Paziente":
			obj_paz.put("nome", nome);
			obj_paz.put("cognome", cognome);
			obj_paz.put("nascita", nascita);
			obj_paz.put("sesso", sesso);
			obj_paz.put("prenotazione", prenotazione);
			typeofdb_paz.put("Paziente", obj_paz);
			paziente.add(typeofdb_paz);
			break;
		}
		
	}
	/*
	 * 
	 * Parametri :
	 * 
	 * @typeofdb: semplifichiamo la gestione suddividendo i database
	 */
	 @SuppressWarnings("unchecked")
	void readJson(String typeofdb) {
        
        try (FileReader reader = new FileReader(typeofdb + ".json"))
        {
 
            switch(typeofdb) {
    		
    		case "Medico":
    			medico.forEach(emp -> parseObject((JSONObject)emp,typeofdb));
    			break;
    			
    		case "Paziente":
    			paziente.forEach(emp -> parseObject((JSONObject)emp,typeofdb));
    			break;
    		}            
            
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	void writeJson(String typeofdb) {
		
		//Write JSON file
        try (FileWriter file = new FileWriter(typeofdb + ".json")) {
        	
        	switch(typeofdb) {
    		
    		case "Medico":
    			file.write(medico.toJSONString()); 
                file.flush();
                break;
    			
    			
    		case "Paziente":
    			file.write(paziente.toJSONString()); 
                file.flush();
                break;
    		}
            
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	
	}
	
	/*
	 * Parser dati Json
	 * 
	 * Parametri :
	 * 
	 * @database : oggetto Json
	 * @typeofdb : parametro distinzione databse (Medico e Paziente)
	 */
    private static void parseObject(JSONObject database, String typeofdb) 
    {
        	//Get object within list
        	JSONObject Object = (JSONObject) database.get(typeofdb);
        	String nome;
        	
        	switch(typeofdb) {
    		
    			case "Medico":
    				nome = (String) Object.get("nome");   
    				np.add(nome);
    				break;
    			
    			
    			case "Paziente":
    				nome = (String) Object.get("nome");   
    				np.add(nome);
    				break;
    		}
         
        
         
    }
		

	
	public static JsonHelper getIstance() {
		if(istance == null) {
			istance = new JsonHelper();
		}
		
		return istance;
	}
	

    //cleanString is not used
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




