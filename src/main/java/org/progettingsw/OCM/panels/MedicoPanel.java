package org.progettingsw.OCM.panels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MedicoPanel {
    
    JButton addMedico = new JButton("Aggiungi medico");
    JButton listMedico = new JButton("Lista medici");
    JButton saveDBM = new JButton("Salva database");
    
    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(createTextField("Nome:", 150)); // Lunghezza preferita per il campo Nome
        panel.add(createTextField("Cognome:", 150)); // Lunghezza preferita per il campo Cognome
        panel.add(createTextField("Professione:", 100)); // Lunghezza preferita per il campo Data di nascita
        
        panel.add(Box.createVerticalStrut(10)); // Spazio vuoto verticale
        
        panel.add(addMedico);
        panel.add(listMedico);
        panel.add(saveDBM);
        
        
        
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
