package org.progettingsw.OCM;

import javax.swing.JOptionPane;

public class Popup {
	
	public msg mg;
	
	public enum msg {
		OK,
		ERR
	}
	
	String title,testo = "";
	
	public Popup(String testo, msg m){
		
		this.testo = testo; // Messaggio BOX
		this.mg = m;
		
		int messageType;
        switch (m) {
            case OK:
                messageType = JOptionPane.INFORMATION_MESSAGE;
                title = "INFO";
                break;
            case ERR:
                messageType = JOptionPane.ERROR_MESSAGE;
                title = "ERRORE";
                break;
            default:
                messageType = JOptionPane.PLAIN_MESSAGE;
                break;
        }
        
        JOptionPane.showMessageDialog(null, testo, title, messageType);

		
		
	}

}
