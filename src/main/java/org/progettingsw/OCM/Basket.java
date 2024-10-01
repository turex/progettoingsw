package org.progettingsw.OCM;


/*
 * 
 * Inserisco qui le funzioni del carrello
 * 
 */


public class Basket {
	
	int spesaTotale = 0;
	private String visita;
	
	Basket(){
		
		this.visita = visita.toUpperCase();
				
	}
	
	
	private int getTotale(String visita) {
		
		visita = visita.toUpperCase();

		
		if(visita.equals("CHIRURGO"))
			spesaTotale += 30;
		else if(visita.equals("OCULISTA"))
			spesaTotale += 20;
		else if(visita.equals("FISIOTERAPISTA"))
			spesaTotale += 50;
		
		return spesaTotale;
		
		
	}
	

}
