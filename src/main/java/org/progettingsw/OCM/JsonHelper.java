package org.progettingsw.OCM;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

//Uso il Singleton JSon (Medico, paziente)


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
	
	final String PATH = System.getProperty("user.dir"); //Path corrente dell'eseguibile
	
	JSONObject obj_med;
	JSONObject typeofdb_med;
			
	JSONObject obj_paz;
	JSONObject typeofdb_paz;
	
	JSONObject obj_pren;
	JSONObject typeofdb_pren;
	
	static int size = 0; // variabile globale per la dimesione delle liste array dei vari DB

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
	public void addtoJson(String nome, String cognome, String id, String professione,String nascita, String sesso,
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
	public
	void addPrenotazioni(String id_paziente, String id_medico,  String typeofdb) {
		 
			obj_pren.put("id_paziente", id_paziente);
			obj_pren.put("id_medico", id_medico);
			obj_pren.put("prenotazioni", prenotazioni.get(i));
			i++;
			typeofdb_pren.put("Prenotazione", obj_pren);
			prenotazione.add(typeofdb_paz);
			
	 }
	 
	 void readfromJson(String typeofdb) {
		 
		 MedicoBuilder med = new MedicoBuilder();
		 
		 JSONParser parser = new JSONParser();
		 
		 
		 try {
		        Object obj = parser.parse(new FileReader(PATH + "\\" + typeofdb + ".json"));
		        JSONArray jsonArray = (JSONArray) obj;

		        // Utilizziamo un solo ciclo for per iterare sugli elementi dell'array
		        for (int i = 0; i < jsonArray.size(); i++) {
		            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
		            JSONObject db = (JSONObject) jsonObject.get(typeofdb);
		            String cognome = (String) db.get("cognome");
		            String nome = (String) db.get("nome");
		            // E così via per gli altri campi...
		            System.out.println("cognome=" + cognome + "; nome=" + nome);
		            med.setCognome(cognome).setNome(nome).getMedico();
		        }
		    } catch (Exception e) {
		        System.out.println("Error: " + e.getMessage());
		        e.printStackTrace(); // Stampa lo stack trace per avere maggiori dettagli sull'errore
		    }
}
	    
	 
	 
	/*
	 * 
	 * Parametri :
	 * 
	 * @typeofdb: semplifichiamo la gestione suddividendo i database
	 * 
	 */
	 @SuppressWarnings("unchecked")
	void readDb(String typeofdb) {
		         
        
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
            
 
        
                
	}
	 
	 int getlistSize(String type) {
		 
		 size = 0;
		 
		 switch(type) {
 		
 		case "Medico":
 			size = nm.size();
 			break;
		 }
		 
		 return size;
		 
	}
	
	public void writeJson(String typeofdb) {
		
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
                
    		case "Prenotazione":
    			file.write(prenotazione.toJSONString()); 
                file.flush();
                break;
    		}
            
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}
	
	void printnomePaz()
	{
		np.forEach(nomi -> System.out.println(nomi));
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
    				System.out.println("Parsing");
    				nm.add((String) Object.get("nome"));
    				System.out.println(nm.get(0));
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




