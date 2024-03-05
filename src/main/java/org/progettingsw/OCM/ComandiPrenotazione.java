package org.progettingsw.OCM;

import java.util.ArrayList;
import java.util.List;

public class ComandiPrenotazione {
	
	List<Prenotazione> pren = new ArrayList<>();
	
	
	void addPrenotazione(PrenotazioneBuilder p) {       
		
		pren.add(p.getPrenotazione());

	}
	
boolean checkdispoMedico(String nome_medico, String cognome_medico, String professione , String data_prenotazione) {
		
		// True se esiste o ci sono errori
	
		/*
		 * 
		 * Controllo che il medico non abbia gia un prenotazione a carico
		 * 
		 * 
		 */
				
		if (nome_medico.isEmpty() || professione.isEmpty() || data_prenotazione.isEmpty())
			return true;
		
		for (int i = 0; i< pren.size(); i++) {
			String check_nomemedico="";
			String check_cognomemedico="";
			String check_professione="";
			String check_dataprenotazione="";
			
			check_nomemedico = pren.get(i).nome_medico.toUpperCase();
			check_cognomemedico = pren.get(i).cognome_medico.toUpperCase();
			check_professione = pren.get(i).professione.toUpperCase();
			check_dataprenotazione = pren.get(i).data;
			
			if(check_nomemedico.equals(nome_medico.toUpperCase()) && check_cognomemedico.equals(cognome_medico.toUpperCase()) &&
					check_professione.equals(professione.toUpperCase()) && check_dataprenotazione.equals(data_prenotazione))
			
				return true;
			
		}
			return false;
		
		
	

}
	
	
	void printMedico(int z) {  
		
		//System.out.println(medi.get(z));
	}
	
boolean checkPrenotazione(String id_paziente, String id_medico, String professione , String data_prenotazione) {
		
		// True se esiste o ci sono errori
	
		/*
		 * 
		 * La prenotazione é valida se il medico é libero o se il paziente non é gia prenotato
		 * 
		 * 
		 */
				
		if (id_paziente.isEmpty() || id_medico.isEmpty() || professione.isEmpty() || data_prenotazione.isEmpty())
			return true;
		
		for (int i = 0; i< pren.size(); i++) {
			String check_nomepaziente="";
			String check_cognomepaziente="";
			String check_nomemedico="";
			String check_cognomemedico="";
			String check_professione="";
			String check_dataprenotazione="";
			
			
			check_nomepaziente = pren.get(i)..toUpperCase();
			check_cognomepaziente = pren.get(i).cognome_paziente.toUpperCase();
			check_nomemedico = pren.get(i).nome_medico.toUpperCase();
			check_cognomemedico = pren.get(i).cognome_medico.toUpperCase();
			check_professione = pren.get(i).professione.toUpperCase();
			check_dataprenotazione = pren.get(i).data;
			
			if(check_nomepaziente.equals(nome_paziente.toUpperCase()) && check_cognomepaziente.equals(cognome_paziente.toUpperCase()) && 
					check_nomemedico.equals(nome_medico.toUpperCase()) && check_cognomemedico.equals(cognome_medico.toUpperCase()) &&
					check_professione.equals(professione.toUpperCase()) && check_dataprenotazione.equals(data_prenotazione))
			
				return true;
			
		}
			return false;
	
}
	
	void listPrenotazioni(String nome_paziente) {  
		
		boolean stato_lista = false; // default la setto false per dire che é vuota
		
		for (int list = 0; list < pren.size(); list++) {
			String check_nome="";
			check_nome = pren.get(list).nome_paziente.toUpperCase() + " " + pren.get(list).cognome_paziente.toUpperCase();
			if(check_nome.equals(nome_paziente.toUpperCase())) {
				System.out.println(pren.get(list));
				stato_lista = true;
			
		}
			
			if(!stato_lista)
				System.out.println("Lista vuota");
		
	  }
	
		
	}
	
}



