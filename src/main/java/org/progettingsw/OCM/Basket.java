package org.progettingsw.OCM;

import java.util.HashMap;

/*
 * 
 * Inserisco qui le funzioni del carrello
 * 
 */


public class Basket {
	
	HashMap<String, Integer> transazione = new HashMap<>(); // Dove mantengo in memoria id e spesa
	
	private static Basket istance; //Singleton istance
	
	
	public static Basket getIstance() {
		if(istance == null) {
			istance = new Basket();
		}
		
		return istance;
	}
	
	
public int addToBasket(String visita, String idpaziente, String livelloMedico) {
		int costo = 0;
		
		int costoMaggiorato = 0, maggiorazione = 0;
    	
    	switch(livelloMedico) {
    	
    	case "ALTA":
    		maggiorazione = 50;
    		break;
    	case "MEDIA":
    		maggiorazione = 30;
    		break;
    	case "BASSA":
    		maggiorazione = 10;
    		break;
    	
    	
    	}
    	
	   switch (visita.toUpperCase()) {
       case "CHIRURGO":
    	   costo = 30;
           break;
       case "OCULISTA":
    	   costo = 20;
           break;
       case "FISIOTERAPISTA":
    	   costo = 50;	   
           break;
       default:
           System.out.println("Tipo di visita non riconosciuto.");
           break;
   }
	   
	   costoMaggiorato = costo + ((costo*maggiorazione)/100);
	   int spesaTotale = transazione.getOrDefault(idpaziente, 0);
       spesaTotale += costoMaggiorato;
       
       transazione.put(idpaziente, spesaTotale);

		return costo;
		
					
	}

public int getTotale(String idpaziente) {
    return transazione.getOrDefault(idpaziente, 0);
}



public void clearBasket(String idpaziente) {

	transazione.remove(idpaziente);

}
	

}
