package org.progettingsw.OCM;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.progettingsw.OCM.panels.CommonPanelUtils;
import org.progettingsw.OCM.panels.ListFrame;

public class ComandiPrenotazione {
    
    static ComandiPrenotazione istance;
    static CommonPanelUtils common = CommonPanelUtils.getInstance();
    static ListFrame lista = new ListFrame();
    
    List<Prenotazione> pren = new ArrayList<>();
    private List<String> salvaPrenotazioni = new ArrayList<>(); // qui salvo le prenotazioni per singolo user da visualizzare
    
    public static ComandiPrenotazione getIstance() {
        if (istance == null) {
            istance = new ComandiPrenotazione();
        }
        return istance;
    }
    
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
    
    public void listPrenotazioni(String id_paziente, PrenotazioneBuilder p) {
        boolean stato_lista = false; // default la setto false per dire che é vuota

        if (pren.size() > 0) {
            common.clearModel(common.model_pren);
            for (int list = 0; list < pren.size(); list++) {
                String check_id = pren.get(list).id_paziente.toUpperCase();
                if (check_id.equals(id_paziente.toUpperCase()) && !isAlreadyOnList(common.model_pren.getDataVector(), p)) {
                    String temp = p.getPrenotazione().toString1();
                    if (temp != null && !temp.isEmpty()) {
                        String[] temp1 = temp.replace("[", "").replace("]", "").split(",");

                        if (temp1.length == 4) {
                            common.setPrenotazioniTableModel(new String[] {temp1[0], temp1[1], temp1[2], temp1[3]});
                            System.out.println(salvaPrenotazioni);

                            stato_lista = true;
                        } else {
                            System.out.println("Errore: La prenotazione non ha il formato corretto.");
                        }
                    } else {
                        System.out.println("Errore: La prenotazione è vuota.");
                    }
                }
            }
            if (!stato_lista) {
                new Popup("Non é stato identificato l'ID", Popup.msgtype.ERR);
            }

            lista.createAndShowFrame("Prenotazione"); // Creo La finestra solo dopo aver aggiunto le prenotazioni al modello della tabella
        } else {
            new Popup("Lista Vuota!", Popup.msgtype.ERR);
        }
    }

    private boolean isAlreadyOnList(Vector dati, PrenotazioneBuilder p) {
        boolean isOnList = false;
        List<String> check = new ArrayList<>(dati);

        String prenotazioneStr = p.getPrenotazione().toString1();
        if (!check.contains(prenotazioneStr) && !salvaPrenotazioni.contains(prenotazioneStr)) {
            salvaPrenotazioni.add(prenotazioneStr);
            System.out.println("Check - " + check + "\n" + "To check: " + prenotazioneStr);
        } else {
            isOnList = true;
        }

        return isOnList;
    }
    
    
    
    
}

	
	/*
	 * Funzionme che utilizzo per estrare la lista dele prenotazioni del user selezionato
	 
	private String[] extractPrenbotatiListbyUserID() {
		
		String[] yetanotherarrayList;
		
		//salvaPrenotazioni.
		
		//salvaPrenotazioni.forEach(dati -> yetanotherarrayList. = dati );
		
		return yetanotherarrayList;
		
		
	}
	
	*/




