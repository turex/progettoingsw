package org.progettingsw.OCM;

import java.util.ArrayList;
import java.util.List;

public class ComandiPrenotazione {
	
	List<Prenotazione> pren = new ArrayList<>();
	
	
	public void addPrenotazione(PrenotazioneBuilder p) {       

		pren.add(p.getPrenotazione());

	}
	
	public boolean checkdispoMedico(String nome_medico, String cognome_medico, String professione, String data_prenotazione) {
	    // True se esiste o ci sono errori
	    if (nome_medico.isEmpty() || professione.isEmpty() || data_prenotazione.isEmpty()) {
	        return true; // Uno dei parametri è vuoto, ci sono errori
	    }

	    for (Prenotazione prenotazione : pren) {
	        if (prenotazione.getidMedico().equalsIgnoreCase(nome_medico) && prenotazione.getProfessione().equalsIgnoreCase(professione) && prenotazione.getData().equals(data_prenotazione)) {
	            return true; // Il medico non è disponibile per la prenotazione
	        }
	    }

	    return false; // Il medico è disponibile per la prenotazione
	}

	
	
	void printMedico(int z) {  
		
		//System.out.println(medi.get(z));
	}
	
	public boolean checkPrenotazione(String id_paziente, String id_medico, String professione, String data_prenotazione) {
	    // True se esiste o ci sono errori
	    if (id_paziente.isEmpty() || id_medico.isEmpty() || professione.isEmpty() || data_prenotazione.isEmpty()) {
	        return true; // Uno dei parametri è vuoto, ci sono errori
	    }

	    for (Prenotazione prenotazione : pren) {
	        if ((prenotazione.getidPaziente().equalsIgnoreCase(id_paziente) && prenotazione.getidMedico().equalsIgnoreCase(id_medico) && prenotazione.getProfessione().equalsIgnoreCase(professione) && prenotazione.getData().equals(data_prenotazione))
	                || (prenotazione.getidMedico().equalsIgnoreCase(id_medico) && prenotazione.getData().equals(data_prenotazione))) {
	            
	        	return true; // La prenotazione esiste già o ci sono errori
	        }
	    }
	    
	    

	    return false; // La prenotazione è valida
	}

	
	public void listPrenotazioni(String id_paziente) {  
		
		boolean stato_lista = false; // default la setto false per dire che é vuota
		
		if(pren.size() > 0) {
		
			for (int list = 0; list < pren.size(); list++) {
				String check_id="";
				check_id = pren.get(list).id_paziente.toUpperCase();
					if(check_id.equals(id_paziente.toUpperCase())) {
						System.out.println(pren.get(list));
						stato_lista = true;
			
					}
			
					if(!stato_lista)
						System.out.println("Non é stato identificato l'ID");
		
				}
			}// End primoi IF
		else
			new Popup("Lista Vuota!", Popup.msg.ERR);
	
		
		}	
	
}



