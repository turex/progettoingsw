package org.progettingsw.OCM.panels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.table.DefaultTableModel;

public class CommonPanelUtils {
	
	public DefaultTableModel model_paz = new DefaultTableModel(new Object[]{"Nome", "Cognome", "Data di Nascita", "Sesso","ID"}, 0);
	public DefaultTableModel model_med = new DefaultTableModel(new Object[]{"Nome", "Cognome", "Professione", "ID"}, 0);
	public DefaultTableModel model_pren = new DefaultTableModel(new Object[]{"ID Paziente", "ID Medico", "Professione", "Data Prenotazione", "Prioritá"},0);
		
	private static CommonPanelUtils instance;
        
	
	 JTextField createTextField(String labelText) {
	        JTextField textField = new JTextField();
	        return textField;
	    }
	    
	     JSpinner createSpinner(Object values) {
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
			String[] newData;
	    	newData = convertToUpperCase(data);
	        model_paz.addRow(newData);
	    }

	    public void setMediciTableModel(String[] data) {
	    	
	    	String[] newData;
	    	newData = convertToUpperCase(data);
	        model_med.addRow(newData);
	        
	    }
	    
	    public void setPrenotazioniTableModel(String[] data) {
	    	String[] newData;
	    	newData = convertToUpperCase(data);
	    	model_pren.addRow(newData);
	    }
	    
	    public void setModel(JTable table, DefaultTableModel model) {
	    	table.setModel(model);
	    }
	    
	    
	    private String[] convertToUpperCase(String[] data) {
	        ArrayList<String> list = new ArrayList<>();

	        for (String valore : data) {
	        	//DEBUG //System.out.println("Dati : " + valore);
	            list.add(valore.toUpperCase());
	        }

	        return list.toArray(new String[0]);
	    }

		public void clearTableModel(DefaultTableModel model) {
			
			model.setRowCount(0);
			
		}
		
		
		public boolean containsPrenotazione(String[] prenotazioneData) {
	        // Check if the table model already contains the appointment
	        for (int i = 0; i < model_pren.getRowCount(); i++) {
	            boolean match = true;
	            for (int j = 0; j < prenotazioneData.length; j++) {
	                if (!model_pren.getValueAt(i, j).equals(prenotazioneData[j])) {
	                    match = false;
	                    break;
	                }
	            }
	            if (match) {
	                return true;
	            }
	        }
	        return false;
	    }
	    
}
