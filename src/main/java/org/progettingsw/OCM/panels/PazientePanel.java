package org.progettingsw.OCM.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import java.util.Calendar;

public class PazientePanel {
    
    JButton addPaziente = new JButton("Aggiungi paziente");
    JButton listPaziente = new JButton("Lista pazienti");
    JButton saveDBP = new JButton("Salva database");
    
    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(createTextField("Nome:", 150)); // Lunghezza preferita per il campo Nome
        panel.add(createTextField("Cognome:", 150)); // Lunghezza preferita per il campo Cognome
        
        // Creazione del JSpinner per selezionare la data di nascita
        JPanel datePanel = new JPanel(new BorderLayout());
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        datePanel.add(new JLabel("Data di nascita:"), BorderLayout.NORTH);
        datePanel.add(dateSpinner, BorderLayout.CENTER);
        panel.add(datePanel);
        
        // Creazione del JSpinner per selezionare il genere
        JPanel genderPanel = new JPanel(new BorderLayout());
        String[] genders = {"M", "F"}; // Opzioni per il genere
        SpinnerListModel genderModel = new SpinnerListModel(genders);
        JSpinner genderSpinner = new JSpinner(genderModel);
        genderPanel.add(new JLabel("Genere:"), BorderLayout.NORTH);
        genderPanel.add(genderSpinner, BorderLayout.CENTER);
        panel.add(genderPanel);
        
        panel.add(Box.createVerticalStrut(10)); // Spazio vuoto verticale
        
        panel.add(addPaziente);
        panel.add(listPaziente);
        panel.add(saveDBP);
        
        addPaziente.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                // Qui inserisci la logica per gestire l'evento del pulsante "Aggiungi paziente"
                // Assicurati di utilizzare le componenti corrette per ottenere i dati dal pannello
                // Esegui il codice per aggiungere un paziente, visualizzare un messaggio di conferma o errore, ecc.
            }
        });
        
        // Aggiungi gli ActionListener per gli altri pulsanti
        
        return panel;
    }
    
    private JPanel createTextField(String labelText, int preferredWidth) {
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.X_AXIS));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(120, 20)); // Imposta una dimensione preferita per l'etichetta
        textFieldPanel.add(label);
        textFieldPanel.add(Box.createHorizontalStrut(10)); // Spazio vuoto orizzontale
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(preferredWidth, 20)); // Imposta una dimensione preferita per il campo di testo
        textFieldPanel.add(textField);
        return textFieldPanel;
    }
}