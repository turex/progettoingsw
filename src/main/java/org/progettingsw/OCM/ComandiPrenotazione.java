package org.progettingsw.OCM;

import java.util.ArrayList;
import java.util.List;

import org.progettingsw.OCM.panels.CommonPanelUtils;
import org.progettingsw.OCM.panels.ListFrame;

public class ComandiPrenotazione {
	
	private static ComandiPrenotazione istance;
	static CommonPanelUtils common = CommonPanelUtils.getInstance();
	static ListFrame lista = new ListFrame();
	List<Prenotazione> pren = new ArrayList<>();
	
	
	public static ComandiPrenotazione getIstance() {
		if(istance == null) {
			istance = new ComandiPrenotazione();
		}
		
		return istance;
	}
	
	
	public void addPrenotazione(PrenotazioneBuilder p) {       

		pren.add(p.getPrenotazione());

	}
	
	public boolean checkdispoMedico(String nome_medico, String professione, String data_prenotazione) {
	    // True se esiste o ci sono errori
	    if (nome_medico.isEmpty() || professione.isEmpty() || data_prenotazione.isEmpty()) {
	        return true; // Uno dei parametri è vuoto, ci sono errori
	    }

	    return pren.stream()
	               .anyMatch(prenotazione -> prenotazione.getidMedico().equalsIgnoreCase(nome_medico) &&
	                                          prenotazione.getProfessione().equalsIgnoreCase(professione) &&
	                                          prenotazione.getData().equals(data_prenotazione));
	}



	
	
	void printMedico(int z) {  
		
		//System.out.println(medi.get(z));
	}
	
	public boolean checkPrenotazione(String id_paziente, String id_medico, String professione, String data_prenotazione) {
	    // True se esiste o ci sono errori
	    if (id_paziente == null || id_medico == null || professione == null || data_prenotazione == null) {
	        System.out.println("Errore: uno dei parametri è null");
	        return true; // Uno dei parametri è vuoto, ci sono errori
	    }

	    System.out.println("Parametri ricevuti: " + id_paziente + ", " + id_medico + ", " + professione + ", " + data_prenotazione);

	    boolean prenotazioneEsistente = pren.stream()
		        .peek(prenotazione -> {

		        	System.out.println("Controllando prenotazione: " + prenotazione);

		        		  System.out.println(prenotazione);
		        }
		        		)
		        .anyMatch(prenotazione -> 
		            (prenotazione.getidPaziente().equalsIgnoreCase(id_paziente) 
		                && prenotazione.getidMedico().equalsIgnoreCase(id_medico) 
		                && prenotazione.getProfessione().equalsIgnoreCase(professione) 
		                && prenotazione.getData().equals(data_prenotazione))
		        );

	    if (prenotazioneEsistente) {
	        return true; // La prenotazione esiste già o ci sono errori
	    }

	    return false; // La prenotazione è valida
	}



	
	public void listPrenotazioni(String id_paziente, PrenotazioneBuilder p) {
	    boolean stato_lista = false; // default set to false to indicate the list is empty

	    if (pren != null && pren.size() > 0) {
	        common.clearTableModel(common.model_pren); // Clear the table model before adding new entries

	        for (int list = 0; list < pren.size(); list++) {
	            String check_id = pren.get(list).id_paziente.toUpperCase();
	            if (check_id.equals(id_paziente.toUpperCase())) { // Print only those with matching patient ID
	                String[] prenotazioneData = new String[] {
	                    pren.get(list).id_paziente,
	                    pren.get(list).id_medico,
	                    pren.get(list).professione,
	                    pren.get(list).data
	                };

	                if (!common.containsPrenotazione(prenotazioneData)) { // Check if the appointment is already in the model
	                    common.setPrenotazioniTableModel(prenotazioneData); // Add the appointment to the table model
	                    stato_lista = true;
	                }
	            }
	        }

	        if (!stato_lista) {
	            new Popup("Non è stata identificata alcuna prenotazione associata all'ID", Popup.msgtype.ERR);
	        } else {
	            lista.createAndShowFrame("Prenotazione"); // Create the window only after adding appointments to the table model
	        }
	    } else {
	        new Popup("Database vuoto!", Popup.msgtype.ERR);
	    }
	}


}



