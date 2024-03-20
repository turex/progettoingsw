package org.progettingsw.OCM.panels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.attribute.PosixFilePermission;

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
import org.progettingsw.OCM.Popup;

public class MedicoPanel {
    
	JTextField nome, cognome, professione;
    
    JButton addMedico = new JButton("Aggiungi medico");
    JButton listMedici = new JButton("Lista medici");
    JButton saveDBM = new JButton("Salva database");
    

    
	CommonPanelUtils common = new CommonPanelUtils();
    
    static ComandiMedico medcommand = ComandiMedico.getIstance(); //obbligato per design di PrenotazionePanel
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
        panel.add(new JLabel("Professione:"));
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
                        String Id = medcommand.getID(nomeValue,cognomeValue,professioneValue);
            			dbs.addtoJson(nomeValue, cognomeValue, Id ,professioneValue , null, null, "Medico");
            			pp.setMediciListModel(m.getMedico().getNome() + " " + m.getMedico().getCognome() + " " + professioneValue);
            			new Popup("Medico aggiunto!", Popup.msg.OK);
                    } else {
            			new Popup("Errore!\nNome, cognome e data di nascita sono necessari o medico gia registrato",Popup.msg.ERR);
                    }
                } else {
                	new Popup("Errore!\nInserire tutti i campi obbligatori", Popup.msg.ERR);
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
                new Popup("Database salvato con successo!", Popup.msg.OK);
            }
        });

        return panel;
   
}
    
}