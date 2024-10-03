package org.progettingsw.OCM;

import java.util.HashMap;

/*
 * 
 * Inserisco qui le funzioni del carrello
 * 
 */


public class Basket {
	
	HashMap<String, Integer> transazione = new HashMap<>(); // Dove mantengo in memoria id e spesa
	
	
public int addToBasket(String visita, String idpaziente) {
		int costo = 0;
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
	   int spesaTotale = transazione.getOrDefault(idpaziente, 0);
       spesaTotale += costo;
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
