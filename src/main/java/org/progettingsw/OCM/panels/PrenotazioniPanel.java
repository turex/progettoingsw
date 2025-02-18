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

import org.progettingsw.OCM.Basket;
import org.progettingsw.OCM.ComandiMedico;
import org.progettingsw.OCM.ComandiPrenotazione;
import org.progettingsw.OCM.Popup;
import org.progettingsw.OCM.Popup.msgtype;
import org.progettingsw.OCM.PrenotazioneBuilder;

public class PrenotazioniPanel {

    String selectProfessione;
    String selectPaziente;
    
    Basket basket = Basket.getIstance();

    static CommonPanelUtils common = CommonPanelUtils.getInstance();

    JTable pazientiTable;
    JTable professionistiTable;
    
    String[] split_paziente = {};
    String[] split_medico = {};
    
    int costo;

    private static PrenotazioniPanel instance;
    
    static ComandiMedico commed = ComandiMedico.getIstance();
    static ComandiPrenotazione commpren = ComandiPrenotazione.getIstance();
        
    JSpinner prioritySpinner; // spinner per impostare prioritá della visita

    static String formattedDate;
    static JSpinner spinner;
    
    static Date selectedDate;
    
    static SimpleDateFormat dateFormat;
    
    PrenotazioneBuilder pb = new PrenotazioneBuilder();
    String priorityValue = "";
    
    private PrenotazioniPanel() {
    	
        pazientiTable = new JTable(common.model_paz){
            /**
			 * 
			 */
			private static final long serialVersionUID = -9193741106200329034L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disabilita l'editabilità basata su isEditable
            }
        };
        
        
        professionistiTable = new JTable(common.model_med){
            /**
			 * 
			 */
			private static final long serialVersionUID = -6017307607317755116L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disabilita l'editabilità basata su isEditable
            }
        };
        
        prioritySpinner = common.createSpinner(common.livello);
        
        
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

        JButton addPrenotazione, listPrenotazioni, paga;

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
        buttonPanel.add(paga = new JButton("Vai al pagamento"));
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
               
                priorityValue = prioritySpinner.getValue().toString();
                                
                if ((selectProfessione == null) || selectProfessione.isEmpty() || (selectPaziente == null) || selectPaziente.isEmpty()) {
                    new Popup("Seleziona medico e paziente!", Popup.msgtype.ERR);
                    return;
                }


                if (commpren.checkPrenotazione(split_paziente[4], split_medico[4], split_medico[2], formattedDate.toString(), priorityValue)) {
                	
                	
                	
                costo = basket.addToBasket(split_medico[2], split_paziente[4], split_medico[3], split_paziente[2]);
                
                
                	commpren.addPrenotazione( pb.setidPaziente(split_paziente[4])
                            .setidMedico(split_medico[4])
                            .setProfessione(split_medico[2])
                            .setData(formattedDate.toString())
                            .setPriority(priorityValue)
                            .setCosto(costo)
                			);
                	                                 
                    common.setPrenotazioniTableModel(new String[] {split_paziente[4], split_medico[4], split_medico[3], formattedDate.toString(), priorityValue,
                    		Integer.toString(costo)
                    		});  //questo popola la tabella
                  
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
                    	commpren.listPrenotazioni(split_paziente[4], pb,costo);
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
                                                professionistiTable.getValueAt(selectedRow, 3) + " " +
                                                professionistiTable.getValueAt(selectedRow, 4)
                                                ;
                            
                            split_medico = selectProfessione.split(" ");
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        
        
        paga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame paymentFrame = new JFrame("Pagamento");
            	PaymentPanel paymentPanel;
            	
            	if (split_paziente != null && split_paziente.length > 4 && !split_paziente[4].isEmpty()) { // Ensure split_paziente is properly initialized and has enough elements
            	paymentPanel = new PaymentPanel(split_paziente[4]);
            	
            	paymentFrame.add(paymentPanel.createPanel());
            	paymentFrame.setSize(400, 300);
            	paymentFrame.setVisible(true);
             }
            	else
            		new Popup("Seleziona il paziente", msgtype.ERR);
            	
            }
        });
        

        return panel;
    } 
       

}
