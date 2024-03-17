package org.progettingsw.OCM.panels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
    
	TextFieldWithLabel nome, cognome, professione;
    
    JButton addMedico = new JButton("Aggiungi medico");
    JButton listMedici = new JButton("Lista medici");
    JButton saveDBM = new JButton("Salva database");
    
    static ComandiMedico medcommand = new ComandiMedico();
    static MedicoBuilder m = new MedicoBuilder();
    static JsonHelper dbs = JsonHelper.getIstance(); // creo oggetto JSON
    PrenotazioniPanel pp = PrenotazioniPanel.getIstance();

    JList<String> lprof = new JList<String>(); // Lista medici
    static String selectionProfessione; // mi da l'item della professione

    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10)); // GridLayout con 2 colonne e 10 pixel di spazio tra le righe e le colonne

        nome = createTextField("Nome:", 150); // Lunghezza preferita per il campo Nome
        cognome = createTextField("Cognome:", 150); // Lunghezza preferita per il campo Cognome
        professione = createTextField("Professione:", 100); // Lunghezza preferita per il campo Data di nascita

        panel.add(nome.getLabel());
        panel.add(nome);
        panel.add(cognome.getLabel());
        panel.add(cognome);
        panel.add(professione.getLabel());
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
                        lprof.setModel(medcommand.listaMedicitoString());
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

        lprof.addListSelectionListener(new ListSelectionListener() { //Setto il Listener per selezione dati da JList MEDICO
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    try {
                        if (!(lprof.getSelectedValue() == null)) {
                            pp.selectProfessione = lprof.getSelectedValue().toString();
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    System.out.println(selectionProfessione);
                }
            }
        });

        return panel;
    }

    private TextFieldWithLabel createTextField(String labelText, int preferredWidth) {
        return new TextFieldWithLabel(labelText, preferredWidth);
    }

    private class TextFieldWithLabel extends JPanel {
        private JLabel label;
        private JTextField textField;

        public TextFieldWithLabel(String labelText, int preferredWidth) {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            label = new JLabel(labelText);
            textField = new JTextField();
            textField.setPreferredSize(new Dimension(preferredWidth, 20));
            add(label);
            add(Box.createRigidArea(new Dimension(10, 0))); // Spazio orizzontale tra la label e il textField
            add(textField);
        }

        public JLabel getLabel() {
            return label;
        }

        public String getText() {
            return textField.getText();
        }
   
}
}