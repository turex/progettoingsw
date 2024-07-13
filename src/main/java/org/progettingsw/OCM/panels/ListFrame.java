package org.progettingsw.OCM.panels;

import java.awt.BorderLayout;
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
        
    DefaultTableModel model;
    
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
  
    }
    
  

    public void createAndShowFrame(String tipo) {
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
        //} else if ("Prenotazione".equals(tipo)) {
            //model = (DefaultTableModel) common.model_pren; // Imposta il modello di tabella per le prenotazioni
        } 

      
        tabella = new JTable(model){
          /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int row, int column) {
                return false; // Disabilita l'editabilità basata su isEditable
            }
        };
           
        common.setModel(tabella, model);
        
        System.out.println(model.getRowCount());
        
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
    
    
    
}
