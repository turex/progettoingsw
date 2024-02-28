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
	
	private static JsonHelper istance;
	private final String PATH = System.getProperty("user.dir"); // prendo il path corrente
	

	@SuppressWarnings({ "unchecked" })
	private JsonHelper() {
		
		 JSONObject employeeDetails = new JSONObject();
	        employeeDetails.put("firstName", "Lokesh");
	        employeeDetails.put("lastName", "Gupta");
	        employeeDetails.put("website", "howtodoinjava.com");
	         
	        JSONObject employeeObject = new JSONObject(); 
	        employeeObject.put("employee", employeeDetails);
	         
	        //Second Employee
	        JSONObject employeeDetails2 = new JSONObject();
	        employeeDetails2.put("firstName", "Brian");
	        employeeDetails2.put("lastName", "Schultz");
	        employeeDetails2.put("website", "example.com");
	         
	        JSONObject employeeObject2 = new JSONObject(); 
	        employeeObject2.put("employee", employeeDetails2);
	         
	        //Add employees to list
	        JSONArray employeeList = new JSONArray();
	        employeeList.add(employeeObject);
	        employeeList.add(employeeObject2);
	         
	        //Write JSON file
	        try (FileWriter file = new FileWriter("employees.json")) {
	            //We can write any JSONArray or JSONObject instance to the file
	            file.write(employeeList.toJSONString()); 
	            file.flush();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        
	        JSONParser jsonParser = new JSONParser();
	         
	        try (FileReader reader = new FileReader("employees.json"))
	        {
	            //Read JSON file
	            try {
					Object obj = jsonParser.parse(reader);
				} catch (org.json.simple.parser.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 
	            //JSONArray employeeList = (JSONArray) obj;
	            System.out.println(employeeList);
	             
	            //Iterate over employee array
	            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
	 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        
	    }
	
	
    private static void parseEmployeeObject(JSONObject employee) 
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("employee");
         
        //Get employee first name
        String firstName = (String) employeeObject.get("firstName");    
        System.out.println(firstName);
         
        //Get employee last name
        String lastName = (String) employeeObject.get("lastName");  
        System.out.println(lastName);
         
        //Get employee website name
        String website = (String) employeeObject.get("website");    
        System.out.println(website);
    }
		

	
	public static JsonHelper getIstance() {
		if(istance == null) {
			istance = new JsonHelper();
		}
		
		return istance;
	}
	
	
	private void writeMedico() {
		
		
	}
	
	void writeTest1() {
		pazienteComposer countryObj = new pazienteComposer();  
	    countryObj.name = "India";
	    countryObj.population = 1000000;

	    List<String> listOfStates = new ArrayList<String>();  
	    listOfStates.add("Madhya Pradesh");  
	    listOfStates.add("Maharastra");  
	    listOfStates.add("Rajasthan");  

	    countryObj.states = listOfStates ;  
	    //ObjectMapper mapper = new ObjectMapper();

	    try {  

	        // Writing to a file   
	    	Files.write( // write to file
			        Paths.get(PATH + "\\" + "output.json"), // get path from file
			        Collections.singleton(countryObj.toString()), // transform array to collection using singleton
			        Charset.forName("UTF-8")); // formatting
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  

	  }  
	
	
	void writetest() { // inserisco tutto su un singolo Array JSON , per prendere i dati li splitto
		
		
		// Riferimento Paziente : String nome,String cognome,String ID,String nascita,String sesso, String prenotazione
		//arrpaz.add("Mario Rossi HDHDHD 15/08/89 M SI");
		//arr.add("age: 36");
		
		//arrpaz.add("Aldo Verdi");
		//arr.add("age: 20");
		
		//jpazz.put("Paziente", arrpaz);
	//	System.out.println(jpazz.get("Paziente"));

		
	
	  //  try {
	//.write( // write to file
	//.get(PATH + "\\" + "output.json"), // get path from file
	//.singleton(jpazz.toJson()), // transform array to collection using singleton
	//.forName("UTF-8") // formatting
	//	    );
	//    }
	//    catch (IOException e) {
	    	
	//    }
	   }
	
	/*
	 * 
	 * Parametri 
	 * @split = variabile splittata in input prelevata dal json
	 * @i = indice divisione
	 * 
	 * @clean = stringa ripulita
	 * 
	 */
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

	public void readPazienti() { //Paziente
		
		//Test splitting
				
		//String[] split = jpazz.get("Paziente ").toString().split(" "); // inserisco lo "splitting" sul Array split
		
		//System.out.println(jpazz.size());
		
		try {
			//System.out.println("Nome : " + cleanString(split,0) + " " + "Cognome : " + cleanString(split,1)+ " " + "ID : " + cleanString(split,2));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		
	
	}

	public class pazienteComposer{
		  public String name;
		  public Integer population;
		  public List<String> states;
		@Override
		public String toString() {
			return "Paziente [name=" + name + ", population=" + population + ", states=" + states + "]";
		}
		  
		  
		}
}




