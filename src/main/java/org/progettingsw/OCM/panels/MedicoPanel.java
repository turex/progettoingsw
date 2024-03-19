package org.progettingsw.OCM.panels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.progettingsw.OCM.ComandiMedico;
import org.progettingsw.OCM.JsonHelper;
import org.progettingsw.OCM.MedicoBuilder;

public class MedicoPanel {
    
	JTextField nome, cognome, professione;
    
    JButton addMedico = new JButton("Aggiungi medico");
    JButton listMedici = new JButton("Lista medici");
    JButton saveDBM = new JButton("Salva database");
    

    
	CommonPanelUtils common = new CommonPanelUtils();
    
    static ComandiMedico medcommand = new ComandiMedico();
    static MedicoBuilder m = new MedicoBuilder();
    static JsonHelper dbs = JsonHelper.getIstance(); // creo oggetto JSON
    PrenotazioniPanel pp = PrenotazioniPanel.getIstance();

    static String selectionProfessione; // mi da l'item della professione

    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10)); // GridLayout con 2 colonne e 10 pixel di spazio tra le righe e le colonne

        nome = common.createTextField("Nome:"); // Lunghezza preferita per il campo Nome
        cognome = common.createTextField("Cognome:"); // Lunghezza preferita per il campo Cognome
        professione = common.createTextField("Professione:"); // Lunghezza preferita per il campo Data di nascita

        panel.add(new JLabel("Nome:"));
        panel.add(nome);
        panel.add(new JLabel("Cognome:"));
        panel.add(cognome);
        panel.add(new JLabel("Data di nascita:"));
        panel.add(professione);

        panel.add(addMedico);
        panel.add(listMedici);
        panel.add(saveDBM);

        addMedico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ottenere il contenuto dei campi di testo
                String nomeValue = nome.getText();
                String cognomeValue = cognome.getText();
                String professioneValue = professione.getText();

                // Esegui le azioni necessarie con i valori ottenuti
                if (!nomeValue.isEmpty() && !cognomeValue.isEmpty() && !professioneValue.isEmpty()) {
                    if (!medcommand.checkMedico(nomeValue, cognomeValue, professioneValue)) {
                        medcommand.addMedico(m.setNome(nomeValue).setCognome(cognomeValue).setProfessione(professioneValue));
            			pp.professionistiList.setModel(medcommand.listaMedicitoString());
                        JOptionPane.showMessageDialog(null, "Medico aggiunto!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Errore!\nTutti i campi sono necessari", "ERRORE", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Errore!\nInserire tutti i campi obbligatori", "ERRORE", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        listMedici.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                medcommand.listMedici();
            }
        });

        saveDBM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbs.writeJson("Medico");
            }
        });

        return panel;
   
}
    
}