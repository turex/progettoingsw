package org.progettingsw.OCM;

import javax.swing.JOptionPane;

public class Popup {
	
	public msg mg;
	
	public enum msg {
		OK,
		ERR
	}
	
	String title,testo = "";
	
	public Popup(String title, String testo, msg m){
		
		this.title= title;
		this.testo = testo;
		this.mg = m;
		
		int messageType;
        switch (m) {
            case OK:
                messageType = JOptionPane.INFORMATION_MESSAGE;
                break;
            case ERR:
                messageType = JOptionPane.ERROR_MESSAGE;
                break;
            default:
                messageType = JOptionPane.PLAIN_MESSAGE;
                break;
        }
        
        JOptionPane.showMessageDialog(null, testo, title, messageType);

		
		
	}

}
