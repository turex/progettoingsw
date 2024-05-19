package org.progettingsw.OCM.panels;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListFrame {

    private JTable tabella;
    private String tipo;

    /**
     * Constructor
     *
     * @param tipo the type (either "Medico" or "Paziente") to list various items in a table
     */
    public ListFrame(String tipo) {
        this.tipo = tipo;
        tabella = new JTable();
    }

    public void createAndShowFrame() {
        JFrame f = new JFrame();
        f.setTitle(tipo);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(800, 600);
        f.setLocationRelativeTo(null);

        f.add(new JScrollPane(tabella), BorderLayout.CENTER);

        // Load data into JTable
        loadData();

        f.setVisible(true);
    }

    private void loadData() {
        // Sample data
        List<String[]> data = new ArrayList<>();
        String[] columnNames = {"ID", "Name", "Details"};

        if ("Medico".equalsIgnoreCase(tipo)) {
            // Add sample data for Medico
            data.add(new String[]{"1", "Dr. Mario Rossi", "Cardiologist"});
            data.add(new String[]{"2", "Dr. Lucia Bianchi", "Pediatrician"});
        } else if ("Paziente".equalsIgnoreCase(tipo)) {
            // Add sample data for Paziente
            data.add(new String[]{"1", "Giovanni Verdi", "45 years"});
            data.add(new String[]{"2", "Anna Neri", "30 years"});
        }

        // Convert list to array
        String[][] dataArray = data.toArray(new String[0][]);

        // Set table model
        tabella.setModel(new javax.swing.table.DefaultTableModel(dataArray, columnNames));
    }
}
