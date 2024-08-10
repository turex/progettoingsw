package org.progettingsw.OCM;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.progettingsw.OCM.panels.CommonPanelUtils;
import org.progettingsw.OCM.panels.ListFrame;

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

	
	public static ArrayList<String> np = new ArrayList<String>(); //array del nome paziente
	public static ArrayList<String> cp = new ArrayList<String>(); //array cognome paziente
	public static ArrayList<String> ip = new ArrayList<String>(); //array ID paziente
	public static ArrayList<String> nap = new ArrayList<String>(); //array nascita paziente
	public static ArrayList<String> sp = new ArrayList<String>(); //array sesso paziente
	
	
	public static ArrayList<String> nm = new ArrayList<String>(); //array del nome medico
	public static ArrayList<String> cm = new ArrayList<String>(); //array cognome medico
	public static ArrayList<String> im = new ArrayList<String>(); //array ID medico
	public static ArrayList<String> prop = new ArrayList<String>(); //array professione medico
	
	public static ArrayList<String> id_p = new ArrayList<String>(); //array del id_paziente per prenotazione
	public static ArrayList<String> id_m = new ArrayList<String>(); //array del id_medico per prenotazione
	public static ArrayList<String> ppren = new ArrayList<String>(); //array data prenotazioni
	public static ArrayList<String> profess = new ArrayList<String>(); //array contenente le professioni dei medici nelle prenotazioni
	public static ArrayList<String> priority = new ArrayList<String>(); //array contenente le prioritá dei medici nelle prenotazioni
	
	ComandiMedico med_comm = ComandiMedico.getIstance();
	ComandiPaziente paz_comm = ComandiPaziente.getIstance();
	ComandiPrenotazione pren_comm = ComandiPrenotazione.getIstance();
	ListFrame list_helper = new ListFrame();
	CommonPanelUtils common = CommonPanelUtils.getInstance();
	
	final String PATH = System.getProperty("user.dir"); //Path corrente dell'eseguibile
	
	JSONObject obj_med;
	JSONObject typeofdb_med;
			
	JSONObject obj_paz;
	JSONObject typeofdb_paz;
	
	JSONObject obj_pren;
	JSONObject typeofdb_pren;
	
	JSONObject med,paz; //TODO
	
	static int size = 0; // variabile globale per la dimesione delle liste array dei vari DB
	
	FileReader reader;

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
	void addPrenotazioni(String id_paziente, String id_medico, String professione,  String data,String priorita) {
		 
		    obj_pren = new JSONObject(); // Inizializzazione dell'oggetto obj_pren
		    typeofdb_pren = new JSONObject();
		    
		    obj_pren.put("id_paziente", id_paziente);
			obj_pren.put("id_medico", id_medico);
			obj_pren.put("professione", professione);
			obj_pren.put("data", data);
			obj_pren.put("priorita", priorita);
			typeofdb_pren.put("Prenotazione", obj_pren);
			prenotazione.add(typeofdb_pren);
			
	 }

	 /*
	  * 
	  * In questa funzione leggo i file json salvati da disco e li carico in memoria
	  */
	 
	 void readfromJson(String typeofdb) {
		 
		 MedicoBuilder medb = new MedicoBuilder();
		 PazienteBuilder pazb = new PazienteBuilder();
		 PrenotazioneBuilder pren = new PrenotazioneBuilder();
		 
		 JSONParser parser = new JSONParser();
		 
		 
		 try {
		       Object obj = parser.parse(reader = new FileReader(PATH + "\\" + typeofdb + ".json"));
		        JSONArray jsonArray = (JSONArray) obj;
		        
		        int i;

		        // Utilizziamo un solo ciclo for per iterare sugli elementi dell'array
		        for (i = 0; i < jsonArray.size(); i++) {
		           JSONObject jsonObject = (JSONObject) jsonArray.get(i);
		            JSONObject db = (JSONObject) jsonObject.get(typeofdb);
		            
		            switch(typeofdb) {
		            
		           case ("Medico"):
		        	 cm.add((String) db.get("cognome"));
		             nm.add((String) db.get("nome"));
		             prop.add((String) db.get("professione"));
		             im.add((String) db.get("id"));
		            
		           med_comm.addMedico(medb.setNome(nm.get(i))
		        		   		     .setCognome(cm.get(i))
		        		             .setProfessione(prop.get(i))
		        		             ); //Lo aggiungo alla lista dei Medici
		           
		           addtoJson(nm.get(i), cm.get(i), im.get(i), prop.get(i), null, null, typeofdb); // Aggiungo i dati alla lista dei JSON (per salvataggio)
		           
		           
		           common.setMediciTableModel(new String[] {
		        		   nm.get(i), cm.get(i), prop.get(i),im.get(i)}
		           );  // Where i add data to the MODEL
		           
		            
		            break;
		            
		            case ("Paziente"):
		            	cp.add((String) db.get("cognome"));
		                np.add((String) db.get("nome"));
		                nap.add((String) db.get("nascita"));
		                sp.add((String) db.get("sesso"));
		                ip.add((String) db.get("id"));

		            paz_comm.addPaziente(pazb.setNome(np.get(i))
		            		.setCognome(cp.get(i))
		            		.setNascita(nap.get(i))
		            		.setSesso(sp.get(i))
		            		);
		                    
		            addtoJson(np.get(i), cp.get(i), ip.get(i), null, nap.get(i), sp.get(i), typeofdb); // Aggiungo i dati alla lista dei JSON (per salvataggio)
			           
			           common.setPazientiTableModel(new String[] {
			        		   np.get(i), cp.get(i),nap.get(i),sp.get(i),ip.get(i)}
					           );  // Where i add data to the MODEL
		            
		            break;
		            
		            
		            case ("Prenotazione"):
		            	
			           id_p.add((String) db.get("id_paziente"));
			           id_m.add((String) db.get("id_medico"));
			           profess.add((String) db.get("professione"));
			           ppren.add((String) db.get("data"));
			           priority.add((String) db.get("priorita"));
			            
			           pren_comm.addPrenotazione(pren.setidPaziente(id_p.get(i))
			        		   .setidMedico(id_m.get(i))
			        		   .setProfessione(profess.get(i))
			        		   .setData(ppren.get(i))
			        		   .setPriority(typeofdb));
			           
			           addPrenotazioni(id_p.get(i), id_m.get(i), profess.get(i), ppren.get(i),priority.get(i)); //Aggiungo la prenotazione al JSON per il salvataggio
			           
			           common.setPrenotazioniTableModel(new String[] {
			        		   id_p.get(i), id_m.get(i),profess.get(i),ppren.get(i), priority.get(i)});
	
			          break;
		            
		            
		            
		            }
		            
		        }
		        
		   reader.close();
		        
		    } catch (Exception e) {
		        System.out.println("Errore: " + e.getMessage());
		        e.printStackTrace(); // Stampa lo stack trace per avere maggiori dettagli sull'errore
		    }
}
	    
	 void printnomePaz()
	        {
	                np.forEach(nomi -> System.out.println(nomi));
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
             * Equivalente di for (JSONArray med : medico){}
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
		    JSONArray data;
		    String filename;
		    switch (typeofdb) {
		        case "Medico":
		            data = medico;
		            filename = "Medico.json";
		            break;
		        case "Paziente":
		            data = paziente;
		            filename = "Paziente.json";
		            break;
		        case "Prenotazione":
		            data = prenotazione;
		            filename = "Prenotazione.json";
		            break;
		        default:
		            throw new IllegalArgumentException("Invalid database type: " + typeofdb);
		    }

		    if (data.isEmpty()) {
		        new Popup("Database vuoto", Popup.msgtype.ERR);
		        return;
		    }

		    try (FileWriter file = new FileWriter(filename)) {
		        file.write(data.toJSONString());
		        file.flush();
		        new Popup("Database salvato con successo!", Popup.msgtype.OK);
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
	 * @typeofdb : parametro distinzione database (Medico e Paziente)
	 */
    private static void parseObject(JSONObject database, String typeofdb) 
    {
        	//Get object within list
    	
    	try {
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
    				
    				break;
    				
    			case "Prenotazione":  
    				id_p.add((String) Object.get("id_paziente"));
    				id_m.add((String) Object.get("id_medico"));
    				ppren.add((String) Object.get("prenotazione"));
    				nap.add((String) Object.get("nascita"));
    				sp.add((String) Object.get("sesso"));
    				break;
    		}
         
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
         
    }
		

	
	public static JsonHelper getIstance() {
		if(istance == null) {
			istance = new JsonHelper();
		}
		
		return istance;
	}
	

}




