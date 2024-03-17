package org.progettingsw.OCM.panels;

import java.awt.BorderLayout;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class PrenotazioniPanel {

    public JPanel createPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Creazione del pannello per i pazienti a sinistra
        JPanel pazientiPanel = new JPanel();
        pazientiPanel.setLayout(new BorderLayout());
        pazientiPanel.add(new JLabel("Lista Pazienti:"), BorderLayout.NORTH);
        JList<String> pazientiList = new JList<>();
        JScrollPane pazientiScrollPane = new JScrollPane(pazientiList);
        pazientiPanel.add(pazientiScrollPane, BorderLayout.CENTER);

        // Creazione del pannello per i professionisti a destra
        JPanel professionistiPanel = new JPanel();
        professionistiPanel.setLayout(new BorderLayout());
        professionistiPanel.add(new JLabel("Lista Professionisti:"), BorderLayout.NORTH);
        JList<String> professionistiList = new JList<>();
        JScrollPane professionistiScrollPane = new JScrollPane(professionistiList);
        professionistiPanel.add(professionistiScrollPane, BorderLayout.CENTER);

        // Pannello per i bottoni
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("Aggiungi prenotazione"));
        buttonPanel.add(new JButton("Lista prenotazioni"));

        // Pannello per la selezione della data e dell'ora
        JPanel prenotazioniPanel = new JPanel(new BorderLayout());
        SpinnerDateModel spinnerModel = new SpinnerDateModel();
        spinnerModel.setCalendarField(Calendar.MINUTE); // Impostazione del campo del calendario su minuti
        JSpinner spinner = new JSpinner(spinnerModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy HH:mm:ss");
        spinner.setEditor(dateEditor);
        prenotazioniPanel.add(new JLabel("Data e ora prenotazione:"), BorderLayout.NORTH);
        prenotazioniPanel.add(spinner, BorderLayout.CENTER);

        // Aggiunta dei pannelli al pannello principale
        panel.add(pazientiPanel, BorderLayout.WEST);
        panel.add(professionistiPanel, BorderLayout.EAST);
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(prenotazioniPanel, BorderLayout.SOUTH);

        return panel;
    }
}