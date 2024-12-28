package org.progettingsw.OCM;

import javax.swing.JOptionPane;

public class Popup {
	
	public msgtype mg;
	
	public enum msgtype {
		OK,
		YES_NO_OPTION,
		ERR
	}
	
	String title,testo = "";
	
public Popup(String testo, msgtype m){
		
		this.testo = testo; // Messaggio BOX
		this.mg = m;
		
		showPopup(testo,m);
		
	}

public static int showPopup(String testo, msgtype m) {
    String title = "";
    int messageType;

    switch (m) {
        case OK:
            messageType = JOptionPane.INFORMATION_MESSAGE;
            title = "INFO";
            JOptionPane.showMessageDialog(null, testo, title, messageType);
            return JOptionPane.OK_OPTION; // Restituisce un valore neutro per OK
        case ERR:
            messageType = JOptionPane.ERROR_MESSAGE;
            title = "ERRORE";
            JOptionPane.showMessageDialog(null, testo, title, messageType);
            return JOptionPane.ERROR; // Restituisce un valore neutro per errore
        case YES_NO_OPTION:
            messageType = JOptionPane.YES_NO_OPTION;
            title = "YES OR NO";
            // Restituisce la risposta dell'utente (YES_OPTION o NO_OPTION)
            return JOptionPane.showConfirmDialog(null, testo, title, messageType);
        default:
            messageType = JOptionPane.PLAIN_MESSAGE;
            title = "Default";
            JOptionPane.showMessageDialog(null, testo, title, messageType);
            return JOptionPane.CLOSED_OPTION; // Caso di chiusura senza scelta
    }
}


}
