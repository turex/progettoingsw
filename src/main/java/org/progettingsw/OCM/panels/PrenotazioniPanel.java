package org.progettingsw.OCM.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.progettingsw.OCM.ComandiMedico;
import org.progettingsw.OCM.ComandiPrenotazione;
import org.progettingsw.OCM.JsonHelper;
import org.progettingsw.OCM.Popup;
import org.progettingsw.OCM.PrenotazioneBuilder;

public class PrenotazioniPanel {

    String selectProfessione;
    String selectPaziente;

    static CommonPanelUtils common = CommonPanelUtils.getInstance();

    JTable pazientiTable;
    JTable professionistiTable;
    
    String[] split_paziente = {};
    String[] split_medico = {};

    private static PrenotazioniPanel instance;
    
    static ComandiMedico commed = ComandiMedico.getIstance();
    static ComandiPrenotazione commpren = ComandiPrenotazione.getIstance();
    static JsonHelper jhelper = JsonHelper.getIstance();
    
    JSpinner prioritySpinner; // spinner per impostare prioritá della visita

    
    String formattedDate;
    JSpinner spinner;
    
    Date selectedDate;
    
    SimpleDateFormat dateFormat;
    
    PrenotazioneBuilder pb = new PrenotazioneBuilder();
    
    private PrenotazioniPanel() {
    	
        pazientiTable = new JTable(common.model_paz){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disabilita l'editabilità basata su isEditable
            }
        };
        
        
        professionistiTable = new JTable(common.model_med){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disabilita l'editabilità basata su isEditable
            }
        };
        
        prioritySpinner = common.createSpinner(new String[]{"1", "2","3"});
        
        
        SpinnerDateModel spinnerModel = new SpinnerDateModel();
        spinnerModel.setCalendarField(Calendar.MINUTE); // Impostazione del campo del calendario su minuti
        spinner = new JSpinner(spinnerModel);
        selectedDate = (Date) spinner.getValue();
        
        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm"); // Nuovo formato desiderato (per confronto database)
        formattedDate = dateFormat.format(selectedDate);
    }

    public static PrenotazioniPanel getInstance() {
        if (instance == null) {
            instance = new PrenotazioniPanel();
        }
        return instance;
    }

    public JPanel createPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JButton addPrenotazione, listPrenotazioni, salvaDB;

        // Creazione del pannello per i pazienti a sinistra
        JPanel pazientiPanel = new JPanel();
        pazientiPanel.setLayout(new BorderLayout());
        pazientiPanel.add(new JLabel("Lista Pazienti:"), BorderLayout.NORTH);
        
        

        JScrollPane pazientiScrollPane = new JScrollPane(pazientiTable);
        pazientiPanel.add(pazientiScrollPane, BorderLayout.CENTER);

        // Creazione del pannello per i professionisti a destra
        JPanel professionistiPanel = new JPanel();
        professionistiPanel.setLayout(new BorderLayout());
        professionistiPanel.add(new JLabel("Lista Professionisti:"), BorderLayout.NORTH);
        JScrollPane professionistiScrollPane = new JScrollPane(professionistiTable);
        professionistiPanel.add(professionistiScrollPane, BorderLayout.CENTER);

        // Pannello per i bottoni
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addPrenotazione = new JButton("Aggiungi prenotazione"));
        buttonPanel.add(listPrenotazioni = new JButton("Lista prenotazioni"));
        buttonPanel.add(salvaDB = new JButton("Salva DB"));
        buttonPanel.add(new JLabel("Prioritá visita: "));
        buttonPanel.add(prioritySpinner);

        // Pannello per la selezione della data e dell'ora
        JPanel prenotazioniPanel = new JPanel(new BorderLayout());
        
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy HH:mm");
        spinner.setEditor(dateEditor);
        prenotazioniPanel.add(new JLabel("Data e ora prenotazione:"), BorderLayout.NORTH);
        prenotazioniPanel.add(spinner, BorderLayout.CENTER);

        // Aggiunta dei pannelli al pannello principale
        panel.add(pazientiPanel, BorderLayout.WEST);
        panel.add(professionistiPanel, BorderLayout.EAST);
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(prenotazioniPanel, BorderLayout.SOUTH);

        addPrenotazione.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                String priorityValue = prioritySpinner.getValue().toString();
                

                if ((selectProfessione == null) || selectProfessione.isEmpty() || (selectPaziente == null) || selectPaziente.isEmpty()) {
                    new Popup("Seleziona medico e paziente!", Popup.msgtype.ERR);
                    return;
                }


                if (!commpren.checkPrenotazione(split_paziente[0], split_medico[3], split_medico[2], formattedDate.toString()) &&
                        !commpren.checkdispoMedico(split_medico[0], split_medico[2], formattedDate.toString())) {
                    
                	commpren.addPrenotazione( pb.setidPaziente(split_paziente[4])
                            .setidMedico(split_medico[3])
                            .setProfessione(split_medico[2])
                            .setData(formattedDate.toString())
                            .setPriority(priorityValue));
                	
                    jhelper.addPrenotazioni(split_paziente[4], split_medico[3], split_medico[2], formattedDate.toString(),priorityValue); // ID paziente, ID Medico , Professione e data prenotazione
                    
                    common.setPrenotazioniTableModel(new String[] {split_paziente[4], split_medico[3], split_medico[2], formattedDate.toString(), priorityValue});
                  
                    new Popup("Prenotazione aggiunta!", Popup.msgtype.OK);
                } else {
                    new Popup("Prenotazione già presente, medico non disponibile o campi vuoti", Popup.msgtype.ERR);
                }
            }
        });

        listPrenotazioni.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectPaziente == null || selectPaziente.isEmpty()) {
                    new Popup("Seleziona Paziente", Popup.msgtype.ERR);
                    return;
                }
                try {
                    
                    if (split_paziente != null || split_medico != null) {
                    	commpren.listPrenotazioni(split_paziente[4], pb);
                    }
                } catch (NullPointerException e1) {
                    e1.printStackTrace();
                }
            }
        });

        pazientiTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg0) {
            	selectPaziente = "";
            	Arrays.fill(split_paziente, "");
                if (!arg0.getValueIsAdjusting()) {
                    try {
                        int selectedRow = pazientiTable.getSelectedRow();
                        if (selectedRow >= 0) {
                        	
                        	
                        	
                            selectPaziente = pazientiTable.getValueAt(selectedRow, 0) + " " +
                                             pazientiTable.getValueAt(selectedRow, 1) + " " +
                                             pazientiTable.getValueAt(selectedRow, 2) + " " +
                                             pazientiTable.getValueAt(selectedRow, 3) + " " +
                                             pazientiTable.getValueAt(selectedRow, 4); // Assicurati di ottenere l'ID
                            
                            split_paziente = selectPaziente.split(" ");
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        professionistiTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg0) {
            	
            	selectProfessione = "";
            	Arrays.fill(split_medico, "");
            	
                if (!arg0.getValueIsAdjusting()) {
                    try {
                        int selectedRow = professionistiTable.getSelectedRow();
                        if (selectedRow >= 0) {
                            selectProfessione = professionistiTable.getValueAt(selectedRow, 0) + " " +
                                                professionistiTable.getValueAt(selectedRow, 1) + " " +
                                                professionistiTable.getValueAt(selectedRow, 2) + " " +
                                                professionistiTable.getValueAt(selectedRow, 3);
                            
                            split_medico = selectProfessione.split(" ");
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        salvaDB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	jhelper.writeJson("Prenotazione");
            }
        });

        return panel;
    } 

}
