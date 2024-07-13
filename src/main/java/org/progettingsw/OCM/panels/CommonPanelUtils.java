package org.progettingsw.OCM.panels;

import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.table.DefaultTableModel;

public class CommonPanelUtils {
	
	public DefaultTableModel model_paz = new DefaultTableModel(new Object[]{"Nome", "Cognome", "Data di Nascita", "Sesso","ID"}, 0);
	public DefaultTableModel model_med = new DefaultTableModel(new Object[]{"Nome", "Cognome", "Professione", "ID"}, 0);
	public DefaultTableModel model_pren = new DefaultTableModel(new Object[]{"ID Paziente", "ID Medico", "Professione", "Data Prenotazione"},0);
		
	private static CommonPanelUtils instance;
        
	
	 JTextField createTextField(String labelText) {
	        JTextField textField = new JTextField();
	        return textField;
	    }
	    
	     JSpinner createSpinner(String labelText, Object values) {
	    	JSpinner spinner = new JSpinner();
	    	if (values instanceof String[]) {
	    		spinner.setModel(new SpinnerListModel((String[]) values));
	    	} else if (values instanceof String) {
	    		SpinnerDateModel model = new SpinnerDateModel();
	    		spinner.setModel(model);
	    		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner, (String) values);
	    		spinner.setEditor(dateEditor);
	    	}
	    	return spinner;
	    }

		public static CommonPanelUtils getInstance() {
			if(instance == null)
				instance = new CommonPanelUtils();
			
			return instance;
		}
		
		public void setPazientiTableModel(String[] data) {
	        model_paz.addRow(data);
	    }

	    public void setMediciTableModel(String[] data) {
	        model_med.addRow(data);
	    }
	    
	    public void setPrenotazioniTableModel(String[] data) {
	    		    	
	    	model_pren.addRow(data);
	    }
	    
	    public void setModel(JTable table, DefaultTableModel model) {
	    	table.setModel(model);
	    }
	    
}
