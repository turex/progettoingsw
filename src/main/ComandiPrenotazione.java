package main;

import java.util.ArrayList;
import java.util.List;

public class ComandiPrenotazione {
	
	List<Prenotazione> pren = new ArrayList<>();
	
	
	void addPrenotazione(PrenotazioneBuilder p) {       
		
		pren.add(p.getPrenotazione());

	}
	
	
	void printMedico(int z) {  
		
		//System.out.println(medi.get(z));
	}
	
boolean checkPrenotazione(String nome_paziente, String cognome_paziente, String nome_medico, String cognome_medico, String professione , String data_prenotazione) {
		
		// True se esiste o ci sono errori
				
		if (nome_paziente.isEmpty() || nome_medico.isEmpty() || professione.isEmpty() || data_prenotazione.isEmpty())
			return true;
		
		//TODO :: Implement cognome of paziente and medico
		for (int i = 0; i< pren.size(); i++) {
			String check_nomepaziente="";
			String check_nomemedico="";
			String check_professione="";
			String check_dataprenotazione="";
			
			
			check_nomepaziente = pren.get(i).nome_paziente.toUpperCase();
			check_nomemedico = pren.get(i).nome_medico.toUpperCase();
			check_professione = pren.get(i).professione.toUpperCase();
			check_dataprenotazione = pren.get(i).data;
			
			if(check_nomepaziente.equals(nome_paziente.toUpperCase()) && check_nomemedico.equals(nome_medico.toUpperCase()) && 
					check_professione.equals(professione.toUpperCase()) && check_dataprenotazione.equals(data_prenotazione))
			
				return true;
			
			
		}
			return false;
		
		
	

}
	
	void listPrenotazioni(String nome_paziente) {  
		
		for (int list = 0; list < pren.size(); list++) {
			String check_nome="";
			check_nome = pren.get(list).nome_paziente.toUpperCase();
			
			if(check_nome.equals(nome_paziente.toUpperCase()))
				System.out.println(pren.get(list));
			//else
			//	System.out.println("Lista vuota");
			
		}
		
	}


}
