package org.progettingsw.OCM.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListFrame {
	
	/*
	 * 
	 * Qui creo una nuova Java Frame che mostra la lista dei dati del sistema, cosi da poterli modificare se richiesto dal from (Modifica)
	 * Altrimenti fará solo visualizzare
	 * 
	 */

	CommonPanelUtils common = CommonPanelUtils.getInstance();
    private JTable tabella;
    
    //private static ListFrame instance;
    
    
	/*
	 * public static ListFrame getInstance() {
	 * 
	 * if(instance == null) instance = new ListFrame();
	 * 
	 * return instance; }
	 */
    
    private int i,j = 0;  // Index delle righe per i vari pazientri e medici ( i per i Pazienit, j per i Medici)
    
    List<String[]> dataM = new ArrayList<>(); //Lista dati medico
    List<String[]> dataP = new ArrayList<>(); // Liosta dati paziente
    
    JTable pazientiTable;
    JTable professionistiTable;
    DefaultTableModel model;
    
    String[] columnNamesMedici = {"Nome", "Professione", "ID"};
    String[] columnNamesPazienti = {"Nome", "Cognome", "Data di Naacita", "Sesso", "ID"};
    
    //JButton save = new JButton("Salva modifiche");
    
    //boolean isEditable = false;
    
    /**
     * Constructor
     *
     * @param tipo the type (either "Medico" or "Paziente") to list various items in a table
     */
    
    
    /*
     * 
     * Add parameter to edit table 
     * 
     * @isEditable (true,false) TODO
     */
    public ListFrame() {
        // Inizializzazione delle tabelle con solo colonne definite
    	
    	//this.isEditable = isEditable;
    	       
        if(common.model_med != null)
        common.model_med = new DefaultTableModel(columnNamesMedici, 0);
        
        common.model_paz = new DefaultTableModel(columnNamesPazienti, 0);
        
        // Inizializza il modello di tabella
        model = new DefaultTableModel();

    }
    
  

    public void createAndShowFrame(String tipo, boolean isEditable) {
		JFrame f = new JFrame();
		f.setTitle("Lista " + tipo);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(800, 600);
		f.setLocationRelativeTo(null);
		f.setLayout(new BorderLayout());

		JScrollPane scrollPane;
        if ("Medico".equals(tipo)) {
            model = (DefaultTableModel) common.model_med; // Imposta il modello di tabella per i medici
        } else if ("Paziente".equals(tipo)) {
            model = (DefaultTableModel) common.model_paz; // Imposta il modello di tabella per i pazienti
        } 

      
        tabella = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return isEditable; // Permetti l'editabilità basata su isEditable
            }
        };

        scrollPane = new JScrollPane(tabella);
        f.add(scrollPane, BorderLayout.CENTER);
        //f.add(save,BorderLayout.SOUTH);
        f.setVisible(true);

    }
        

    
    // Metodo per aggiornare la tabella
    public void updateTable() {
        if (model != null) {
            model.fireTableDataChanged(); // Notifica la tabella dei cambiamenti nei dati
        }
    }
    
    public void addPazienteData(DefaultTableModel model, String id, String nome, String cognome, String datadinascita, String sesso) {
    	model.addRow(new Object[]{id, nome, cognome, datadinascita,sesso});
    }
    
    public void addMedicoData(DefaultTableModel model, String id, String nome, String cognome, String specializzazione) {
        model.addRow(new Object[]{id, nome, cognome, specializzazione});
        
        
    }
    
    
}
