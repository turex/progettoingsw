package org.progettingsw.OCM.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ListModel;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.progettingsw.OCM.ComandiPrenotazione;
import org.progettingsw.OCM.JsonHelper;
import org.progettingsw.OCM.Popup;
import org.progettingsw.OCM.PrenotazioneBuilder;

public class PrenotazioniPanel {
	
	String selectProfessione;
	String selectPaziente;
		
    static ComandiPrenotazione prencommand = new ComandiPrenotazione();
	static PrenotazioneBuilder pb = new PrenotazioneBuilder();
	static JsonHelper dbs = JsonHelper.getIstance(); // creo oggetto JSON
	
	JList<String> pazientiList = new JList<>();
    JList<String> professionistiList = new JList<>();

	
	static PrenotazioniPanel istance;
	
	public static PrenotazioniPanel getIstance() {
		if(istance == null)
			istance = new PrenotazioniPanel();
		
		return istance;
	}

    public JPanel createPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JButton addPrenotazione, listPrenotazioni = new JButton();

        // Creazione del pannello per i pazienti a sinistra
        JPanel pazientiPanel = new JPanel();
        pazientiPanel.setLayout(new BorderLayout());
        pazientiPanel.add(new JLabel("Lista Pazienti:"), BorderLayout.NORTH);
        
        JScrollPane pazientiScrollPane = new JScrollPane(pazientiList);
        pazientiPanel.add(pazientiScrollPane, BorderLayout.CENTER);

        // Creazione del pannello per i professionisti a destra
        JPanel professionistiPanel = new JPanel();
        professionistiPanel.setLayout(new BorderLayout());
        professionistiPanel.add(new JLabel("Lista Professionisti:"), BorderLayout.NORTH);
        JScrollPane professionistiScrollPane = new JScrollPane(professionistiList);
        professionistiPanel.add(professionistiScrollPane, BorderLayout.CENTER);

        // Pannello per i bottoni
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addPrenotazione = new JButton("Aggiungi prenotazione"));
        buttonPanel.add(listPrenotazioni = new JButton("Lista prenotazioni"));

        // Pannello per la selezione della data e dell'ora
        JPanel prenotazioniPanel = new JPanel(new BorderLayout());
        SpinnerDateModel spinnerModel = new SpinnerDateModel();
        spinnerModel.setCalendarField(Calendar.MINUTE); // Impostazione del campo del calendario su minuti
        JSpinner spinner = new JSpinner(spinnerModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        spinner.setEditor(dateEditor);
        prenotazioniPanel.add(new JLabel("Data e ora prenotazione:"), BorderLayout.NORTH);
        prenotazioniPanel.add(spinner, BorderLayout.CENTER);

        // Aggiunta dei pannelli al pannello principale
        panel.add(pazientiPanel, BorderLayout.WEST);
        panel.add(professionistiPanel, BorderLayout.EAST);
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(prenotazioniPanel, BorderLayout.SOUTH);
        

        addPrenotazione.addActionListener(new ActionListener(){
        	
        	public void actionPerformed(ActionEvent e) {
        		String[] split_paziente= {};
        		String[] split_medico= {};
        		Date selectedDate = (Date) spinner.getValue();
                String formattedDate = dateFormat.format(selectedDate);
        		
        		if(!(selectProfessione == null) && !(selectPaziente == null)) { // aggiungo la prenotazione se ho selezionato sia paziente che medico ma
        			//non é presente gia una prenotazion
        			split_paziente = selectPaziente.split(" ");
        			split_medico = selectProfessione.split(" ");
        			        		
        			if(!prencommand.checkPrenotazione(split_paziente[2],split_medico[3],split_medico[1], formattedDate)&&
        					!prencommand.checkdispoMedico(split_medico[0],split_medico[1], split_medico[2], formattedDate)) { //se il check é false allora mi aggiunge la prenotazione
        				//System.out.println(prencommand.checkPrenotazione(split_paziente[0],split_paziente[1], split_medico[0],split_medico[1], split_medico[2], data_prenotazione.getText())); //DEBUG

        				
        				prencommand.addPrenotazione(pb.setidPaziente(split_paziente[2]).setidMedico(split_medico[3]).setProfessione(split_medico[2]).setData(formattedDate));
        				dbs.addPrenotazioni(split_paziente[2], split_medico[3], split_medico[2]);
        				
        				
        				new Popup("Prenotazione aggiunta!",Popup.msg.OK);
        			}
        			else
        				new Popup("Prenotazione gia presente , medico non disponibile o campi vuoti", Popup.msg.ERR);
        		}
        		else
        			new Popup("Seleziona medico e paziente!",Popup.msg.ERR);
        		
        		
        	}
        	
        });
        
        listPrenotazioni.addActionListener(new ActionListener(){
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		try
        		{
        			
        		String[] split_paziente = selectPaziente.split(" ");
        			
        		if(!(selectPaziente == null) && !(split_paziente == null)) {
        		prencommand.listPrenotazioni(split_paziente[2]);
        		}
        		else
        			new Popup("Seleziona Paziente",Popup.msg.ERR);
        		}
        		catch (NullPointerException e1)
        		{
        			e1.printStackTrace();
        		}
        	}
        	
        });
        
        
        pazientiList.addListSelectionListener(new ListSelectionListener() { //Setto il Listener per selezione dati da JList PAZIENTEL
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                	try {
                		if(!(pazientiList.getSelectedValue() == null))
                			selectPaziente = pazientiList.getSelectedValue().toString();
                	} catch (NullPointerException e) {
                		e.printStackTrace();
                	}
                    System.out.println(selectPaziente);
                }
            }
        });
        
        
        professionistiList.addListSelectionListener(new ListSelectionListener() { //Setto il Listener per selezione dati da JList MEDICO
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    try {
                        if (!(professionistiList.getSelectedValue() == null)) {
                            selectProfessione = professionistiList.getSelectedValue().toString();
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    System.out.println(selectProfessione);
                }
            }
        });
        

        return panel;
    }
    
    public void setPazientiListModel(DefaultListModel<String> model) {
    	pazientiList.setModel(model);
    	pazientiList.repaint();
    }
    
}