package org.progettingsw.OCM;

import javax.swing.JOptionPane;

public class Popup {
	
	String title,testo = "";
	
	public Popup(String title, String testo){
		
		this.title= title;
		this.testo = testo;
		
		
		JOptionPane.showMessageDialog(
		        null, testo, title, JOptionPane.INFORMATION_MESSAGE);
		
	}

}
