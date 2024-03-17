package org.progettingsw.OCM.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.progettingsw.OCM.ComandiPaziente;
import org.progettingsw.OCM.JsonHelper;
import org.progettingsw.OCM.PazienteBuilder;
import org.progettingsw.OCM.Popup;

public class PazientePanel {
    
    JButton addPaziente = new JButton("Aggiungi paziente");
    JButton listPaziente = new JButton("Lista pazienti");
    JButton saveDBP = new JButton("Salva database");
    
	static String selectPaziente; // mi da l'item del paziente
	
	static ComandiPaziente command = new ComandiPaziente();
	static PazienteBuilder p = new PazienteBuilder();
	static JsonHelper dbs = JsonHelper.getIstance(); // creo oggetto JSON
	
	PrenotazioniPanel pp =  PrenotazioniPanel.getIstance();
	
	JTextField nome,cognome;
	JSpinner dateSpinner, genderSpinner;
	JList<String> lp = new JList<String>(); //Lista pazienti

    
    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10)); // GridLayout con 2 colonne e 10 pixel di spazio tra le righe e le colonne
        
        nome = createTextField("Nome:"); // Lunghezza preferita per il campo Nome
        cognome = createTextField("Cognome:"); // Lunghezza preferita per il campo Cognome
        dateSpinner = createSpinner("Data di nascita:", "dd/MM/yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        genderSpinner = createSpinner("Sesso:", new String[]{"M", "F"});
        
        panel.add(new JLabel("Nome:"));
        panel.add(nome);
        panel.add(new JLabel("Cognome:"));
        panel.add(cognome);
        panel.add(new JLabel("Data di nascita:"));
        panel.add(dateSpinner);
        panel.add(new JLabel("Sesso:"));
        panel.add(genderSpinner);
        
        panel.add(addPaziente);
        panel.add(listPaziente);
        panel.add(saveDBP);
        
        addPaziente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Ottenere il contenuto dei campi di testo e del JSpinner
            	String nomeValue = nome.getText();
            	String cognomeValue = cognome.getText();
            	Date selectedDate = (Date) dateSpinner.getValue();
                String formattedDate = dateFormat.format(selectedDate);            	 
                String sessoValue = genderSpinner.getValue().toString();
            	
            	// Esegui le azioni necessarie con i valori ottenuti
            	if (!nomeValue.isEmpty() && !cognomeValue.isEmpty() && !formattedDate.isEmpty()) {
            		if (!command.checkPaziente(nomeValue, cognomeValue, formattedDate)) {
            			command.addPaziente(p.setNome(nomeValue).setCognome(cognomeValue).setNascita(formattedDate).setSesso(sessoValue));
            			String Id = command.assignID(p);
            			dbs.addtoJson(nomeValue, cognomeValue, Id ,null , formattedDate, sessoValue, "Paziente");
            			lp.setModel(command.listaPazientitoString());
            			new Popup("SUCCESS", "Paziente aggiunto!",Popup.msg.OK);
            		} else {
            			new Popup("ERRORE", "Errore!\nNome, cognome e data di nascita sono necessari o paziente gia registrato",Popup.msg.ERR);
            		}
            	} else {
            		new Popup("ERRORE", "Errore!\nInserire tutti i campi obbligatori", Popup.msg.ERR);
            	}
            }
        });

        listPaziente.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		command.listPazienti();
        	}
        });
        
        saveDBP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dbs.writeJson("Paziente");
			}
        });
        
        lp.addListSelectionListener(new ListSelectionListener() { //Setto il Listener per selezione dati da JList PAZIENTEL
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                	try {
                		if(!(lp.getSelectedValue() == null))
                			pp.selectPaziente = lp.getSelectedValue().toString();
                	} catch (NullPointerException e) {
                		e.printStackTrace();
                	}
                    System.out.println(selectPaziente);
                }
            }
        });
        
        return panel;
    }
    
    private JTextField createTextField(String labelText) {
        JTextField textField = new JTextField();
        return textField;
    }
    
    private JSpinner createSpinner(String labelText, Object values) {
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
}
