package org.progettingsw.OCM;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//Uso il Singleton e il wrapper per creare piu JSon (Medico, paziente)


/*
 * 
 * Struttura Medico :
 * 
 * String nome = "";
	String cognome = "";
	String ID = "";
	String professione = "";
 * 
 */



/*
 * Struttura Paziente :
 * 
 * 	String nome = "";
	String cognome = "";
	String ID = "";
	String nascita = "";
	String sesso = "";
	String prenotazione = "";
 * 
 */

public class JsonHelper {
	
	private static JsonHelper istance; //Singleton istance
	public static JSONArray medico = new JSONArray(); //inserisco qui l'array del JSON per i medici
	public static JSONArray paziente = new JSONArray(); //inserisco qui l'array del JSON per i medici
	public static JSONArray prenotazione = new JSONArray(); //inserisco qui l'array del JSON per le prenotazioni
	public static JSONArray prenotazioni = new JSONArray(); //inserisco qui l'array per array delle prenotazioni

	
	int i = 0; //Index prenotazione paziente per array prenotazioni multiple
	public static ArrayList<String> np = new ArrayList<String>(); //array del nome paziente
	public static ArrayList<String> cp = new ArrayList<String>(); //array cognome paziente
	public static ArrayList<String> ip = new ArrayList<String>(); //array ID paziente
	public static ArrayList<String> nap = new ArrayList<String>(); //array nascita paziente
	public static ArrayList<String> sp = new ArrayList<String>(); //array sesso paziente
	public static ArrayList<String> pp = new ArrayList<String>(); //array prenotazione paziente
	
	
	public static ArrayList<String> nm = new ArrayList<String>(); //array del nome medico
	public static ArrayList<String> cm = new ArrayList<String>(); //array cognome medico
	public static ArrayList<String> im = new ArrayList<String>(); //array ID medico
	public static ArrayList<String> prop = new ArrayList<String>(); //array nascita medico
	
	public static ArrayList<String> id_p = new ArrayList<String>(); //array del id_paziente per prenotazione
	public static ArrayList<String> id_m = new ArrayList<String>(); //array del id_medico per prenotazione
	public static ArrayList<String> ppren = new ArrayList<String>(); //array dprenotazioni per prenotazione
	
	
	JSONObject obj_med;
	JSONObject typeofdb_med;
			
	JSONObject obj_paz;
	JSONObject typeofdb_paz;
	
	JSONObject obj_pren;
	JSONObject typeofdb_pren;

	private JsonHelper() {
	         
	    }
	
	/*
	 * 
	 * Parametri :
	 * 
	 * @obj : componenti del JSON (esempio : nome paziente ecc..)
	 * @typeofdb : inserisco nell'array se é medico o paziente
	 * @employeeList : contiene il database 
	 * @typeofdb : parametrizzo il database, qui viene inserito se é medico o paziente
	 */
	
	 @SuppressWarnings("unchecked")
	void addtoJson(String nome, String cognome, String id, String professione,String nascita, String sesso,
					 String typeofdb) {
		 		
				
		switch(typeofdb) {
		
		case "Medico":
			obj_med = new JSONObject();
			typeofdb_med = new JSONObject();
			obj_med.put("nome", nome);
			obj_med.put("cognome", cognome);
			obj_med.put("id", id);
			obj_med.put("professione", professione);
			typeofdb_med.put("Medico", obj_med);
			medico.add(typeofdb_med);
			break;
			
		case "Paziente":
			obj_paz = new JSONObject();
			typeofdb_paz = new JSONObject();
			obj_paz.put("nome", nome);
			obj_paz.put("cognome", cognome);
			obj_paz.put("id", id);
			obj_paz.put("nascita", nascita.replace("/","-"));
			obj_paz.put("sesso", sesso);
			typeofdb_paz.put("Paziente", obj_paz);
			paziente.add(typeofdb_paz);
			break;
						
			
		}
		
	}
	 
	 
	 @SuppressWarnings("unchecked")
	void addPrenotazioni(String id_paziente, String id_medico,  String typeofdb) {
		 
			obj_pren.put("id_paziente", id_paziente);
			obj_pren.put("id_medico", id_medico);
			obj_pren.put("prenotazioni", prenotazioni.get(i));
			i++;
			typeofdb_pren.put("Prenotazione", obj_pren);
			prenotazione.add(typeofdb_paz);
			
	 }
	 
	/*
	 * 
	 * Parametri :
	 * 
	 * @typeofdb: semplifichiamo la gestione suddividendo i database
	 */
	 @SuppressWarnings("unchecked")
	ArrayList<String> readJson(String typeofdb) {
		 
		 ArrayList<String> list = new ArrayList<String>();
        
        try (FileReader reader = new FileReader(typeofdb + ".json"))
        {
 
            switch(typeofdb) {
            
            /*
             * 
             * Equivalente di for (String med : medico){}
             * 
             */
    		
    		case "Medico":
    			medico.forEach(med -> {
    				parseObject((JSONObject)med,typeofdb);
    					
    			});
    			
    			break;
    			
    		case "Paziente":
    			paziente.forEach(paz -> parseObject((JSONObject)paz,typeofdb));
    			break;
    			
    		case "Prenotazione":
    			prenotazione.forEach(pren -> parseObject((JSONObject)pren,typeofdb));
    			break;
    		}            
            
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Test lista : " + list);
        
        return list;
	}
	
	void writeJson(String typeofdb) {
		
		//Write JSON file
        try (FileWriter file = new FileWriter(typeofdb + ".json")) {
        	
        	switch(typeofdb) {
    		
    		case "Medico":    	
    			file.append(medico.toJSONString()); 
                file.flush();
                break;
    			
    			
    		case "Paziente":
    			file.append(paziente.toJSONString()); 
                file.flush();
                break;
                
    		case "Prenotazione":
    			file.append(prenotazione.toJSONString()); 
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
        	
        	switch(typeofdb) {
    		
    			case "Medico":
    				nm.add((String) Object.get("nome"));
    				cm.add((String) Object.get("cognome"));
    				im.add((String) Object.get("id"));
    				prop.add((String) Object.get("professione"));
    				break;
    			
    			
    			case "Paziente":  
    				np.add((String) Object.get("nome"));
    				cp.add((String) Object.get("cognome"));
    				ip.add((String) Object.get("id"));
    				nap.add((String) Object.get("nascita"));
    				sp.add((String) Object.get("sesso"));
    				pp.add((String) Object.get("prenotazione"));
    				
    				System.out.println(np);
    				break;
    				
    			case "Prenotazione":  
    				id_p.add((String) Object.get("id_paziente"));
    				id_m.add((String) Object.get("id_medico"));
    				ppren.add((String) Object.get("prenotazione"));
    				nap.add((String) Object.get("nascita"));
    				sp.add((String) Object.get("sesso"));
    				pp.add((String) Object.get("prenotazione"));
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




