package org.progettingsw.OCM;

import java.util.ArrayList;
import java.util.List;

import org.progettingsw.OCM.Popup.msgtype;
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
	
	public boolean checkdispoMedico(String nome_medico, String professione, String data_prenotazione, String priorita) {
	    // restiruisco false se esiste o ci sono errori
		if (nome_medico.isEmpty() || professione.isEmpty() || data_prenotazione.isEmpty() || priorita.isEmpty()) {
	        return false; // Uno dei parametri è vuoto, ci sono errori
	    }
	    
	    if (pren == null) {
	        return false; // Lista prenotazioni non inizializzata
	    }

	    // Verifica se esiste una prenotazione per lo stesso medico, professione e data
	    boolean prenotazioneEsistente = pren.stream()
	        .anyMatch(prenotazione -> prenotazione.getidMedico().equalsIgnoreCase(nome_medico) &&
	                                   prenotazione.getProfessione().equalsIgnoreCase(professione) &&
	                                   prenotazione.getData().equals(data_prenotazione));

	    if (prenotazioneEsistente) {
	        // Se esiste una prenotazione, controlla anche la priorità
	        boolean conflittoPriorita = pren.stream()
	            .filter(prenotazione -> 
	                prenotazione.getidMedico().equalsIgnoreCase(nome_medico) && // Stesso medico
	                prenotazione.getProfessione().equalsIgnoreCase(professione) && // Stessa professione
	                prenotazione.getData().equals(data_prenotazione) // Stessa data
	            )
	            .anyMatch(prenotazione -> {
	                // Confronto tra la priorità della prenotazione esistente e quella nuova
	                int prioritaEsistente = getPrioritaValue(prenotazione.getPriority());
	                int prioritaNuova = getPrioritaValue(priorita);
	                return prioritaEsistente < prioritaNuova; // Conflitto se la priorità esistente è più alta (numericamente inferiore)
	            });

	        if (conflittoPriorita) {
	            System.out.println("Errore: il medico ha una prenotazione con priorità più alta nello stesso momento.");
	            new Popup("Attenzione\nIl medico ha già una prenotazione con una priorità più alta per la stessa data.", msgtype.OK);
	            return false; // Se esiste una priorità più alta, non è possibile prenotare
	        }
	    }

	    // Se non ci sono conflitti, la prenotazione è valida
	    return true; // La prenotazione è valida
	}


	// Metodo di supporto per convertire una priorità in un valore numerico
	private int getPrioritaValue(String priorita) {
	    switch (priorita.toUpperCase()) {
	        case "ALTA":
	            return 1; // Priorità più alta
	        case "MEDIA":
	            return 2;
	        case "BASSA":
	            return 3; // Priorità più bassa
	        default:
	            return 0; // Se la priorità non è valida, restituisci 0 per evitare conflitti
	    }
	}
	
	
	public boolean checkPrenotazione(String id_paziente, String id_medico, String professione, String data_prenotazione, String priorita) {
	    // Controlla se uno dei parametri è null o vuoto
	    if (id_paziente == null || id_medico == null || professione == null || data_prenotazione == null || priorita == null
	        || id_paziente.isEmpty() || id_medico.isEmpty() || professione.isEmpty() || data_prenotazione.isEmpty() || priorita.isEmpty()) {

	        new Popup("Errore: uno dei parametri è null o vuoto",msgtype.ERR);
	        return false; // Se uno dei parametri è vuoto o null, la prenotazione non è valida
	    }

	    // Debug: Stampa i parametri ricevuti
	   // System.out.println("Parametri ricevuti: " + id_paziente + ", " + id_medico + ", " + professione + ", " + data_prenotazione + ", " + priorita);

	    // Verifica se esiste una prenotazione che corrisponde ai criteri specificati
	    boolean prenotazioneEsistente = pren.stream()
	        .anyMatch(prenotazione -> 
	            prenotazione.getidPaziente().equalsIgnoreCase(id_paziente) &&
	            prenotazione.getidMedico().equalsIgnoreCase(id_medico) &&
	            prenotazione.getProfessione().equalsIgnoreCase(professione) &&
	            prenotazione.getData().equals(data_prenotazione) &&
	            prenotazione.getPriority().equals(priorita)
	        );

	    // Se la prenotazione esiste già, restituisce false
	    if (prenotazioneEsistente) {
	        new Popup("Prenotazione già esistente.",msgtype.ERR);
	        return false; // La prenotazione esiste già
	    }

	    // Ora controlliamo se il medico è disponibile per la data specificata con la priorità
	    if (!checkdispoMedico(id_medico, professione, data_prenotazione, priorita)) {
	    	new Popup("Errore: il medico non è disponibile per questa data e priorità.",msgtype.ERR);
	        return false; // Il medico non è disponibile, non puoi procedere con la prenotazione
	    }

	    // Se non esiste una prenotazione già registrata e il medico è disponibile, la prenotazione è valida
	    new Popup("Prenotazione effettuata con successo.",msgtype.OK);
	    return true; // La prenotazione è valida
	}



	/*
	 * 
	 * Metodo per creare la frame contenente la lista delle prenotazioni del paziente selezionato
	 */
	public void listPrenotazioni(String id_paziente, PrenotazioneBuilder p, int costo) {
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
	                    pren.get(list).data,
	                    pren.get(list).priority,
	                    Integer.toString(pren.get(list).costo) // aggiungo il costo della visita
	                    
	                    
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



