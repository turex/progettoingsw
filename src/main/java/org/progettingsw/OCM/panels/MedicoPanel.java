package org.progettingsw.OCM.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;

import org.progettingsw.OCM.ComandiMedico;
import org.progettingsw.OCM.MedicoBuilder;
import org.progettingsw.OCM.Popup;

public class MedicoPanel {
    
	JTextField nome, cognome;
    
    JButton addMedico = new JButton("Aggiungi medico");
    JButton listMedici = new JButton("Lista medici");
    
    	
    static ComandiMedico medcommand = ComandiMedico.getIstance(); //obbligato per design di PrenotazionePanel
    static MedicoBuilder m = new MedicoBuilder();
    PrenotazioniPanel pp = PrenotazioniPanel.getInstance();
    
    CommonPanelUtils common = CommonPanelUtils.getInstance();
    
    String[] professioneValues = {"Chirurgo", "Oculista", "Fisioterapista"};
    SpinnerListModel model = new SpinnerListModel(professioneValues);
    JSpinner professione = new JSpinner(model);
   
    JSpinner level = common.createSpinner(common.livello);
    
    ListFrame lista = new ListFrame();

    static String selectionProfessione; // mi da l'item della professione

    public JPanel createPanel() {
    	
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10)); // GridLayout con 2 colonne e 10 pixel di spazio tra le righe e le colonne

        nome = common.createTextField("Nome:"); // Lunghezza preferita per il campo Nome
        cognome = common.createTextField("Cognome:"); // Lunghezza preferita per il campo Cognome
        
        
        // Make the JSpinner non-editable
        JFormattedTextField txt = ((JSpinner.DefaultEditor) professione.getEditor()).getTextField();
        txt.setEditable(false);

        panel.add(new JLabel("Nome:"));
        panel.add(nome);
        panel.add(new JLabel("Cognome:"));
        panel.add(cognome);
        panel.add(new JLabel("Professione:"));
        panel.add(professione);
        panel.add(new JLabel("Livello esperienza:"));
        panel.add(level);

        panel.add(addMedico);
        panel.add(listMedici);

        addMedico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ottenere il contenuto dei campi di testo
                String nomeValue = nome.getText();
                String cognomeValue = cognome.getText();
                String professioneValue = professione.getValue().toString();
                String levelValue = level.getValue().toString();

                // Esegui le azioni necessarie con i valori ottenuti
                if (!nomeValue.isEmpty() && !cognomeValue.isEmpty()) {
                    if (!medcommand.checkMedico(nomeValue, cognomeValue, professioneValue)) {
                    	
                    	
                    	medcommand.addMedico(m.setNome(nomeValue)
                    			.setCognome(cognomeValue)
                    			.setProfessione(professioneValue)
                    			.setLevel(professioneValue)
                    			);
                    	
                    	
                    	 String Id = medcommand.getID(nomeValue,cognomeValue,professioneValue);
            			
            			common.setMediciTableModel(new String[] {m.getMedico().getNome(),m.getMedico().getCognome(), professioneValue,levelValue, Id});  // Where i add data to the MODEL
            			new Popup("Medico aggiunto!", Popup.msgtype.OK);
                    } else {
            			new Popup("Errore!\nNome, cognome e data di nascita sono necessari o medico gia registrato",Popup.msgtype.ERR);
                    }
                } else {
                	new Popup("Errore!\nInserire tutti i campi obbligatori", Popup.msgtype.ERR);
                }
            }
        });

        listMedici.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!medcommand.listMedici())
               // SwingUtilities.invokeLater(() -> {
                    lista.createAndShowFrame("Medico"); // not showed if is empty
                    
              //  });
                
                
            }
        });

       

        return panel;
   
}
    
}