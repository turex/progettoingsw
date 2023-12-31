package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


/*
 * 
 * Classe contenente il main e la parte grafica
 * OCM Opensource clinical manager
 * Gestionale di una clinica, viene simulata la gestione dei pazienti, 
 * dei medici, dei macchinari e delle prenotazioni di una clinica. 
 * 
 * Usiamo il Builder pattern
 * 
 */

public class Interface {
	
	public static void main(String[] args) {

        JFrame frame = new JFrame("OCM Opensource clinical manager");
        ComandiPaziente command = new ComandiPaziente();
        PazienteBuilder p = new PazienteBuilder();
        
        
     // Define new buttons 
        //JButton jb1 = new JButton("Button 1");      
         
         
        // Define the panel to hold the buttons 
        JTabbedPane tabPane = new JTabbedPane();
        
        JPanel panel1 = new JPanel();// Pannello "Paziente" dove vengono inseriti i dati del paziente
        JPanel panel2 = new JPanel();// Pannello "Medico" dove vengono inseriti i dati del Medico
        JPanel panel3 = new JPanel();// Pannello "Macchinario" dove vengono inseriti i dati dei macchinari
        JPanel panel4 = new JPanel();// Pannello "Prenotazioni" dove vengono inseriti i dati delle prenotazioni
        
        
        
       
        JButton addPaziente = new JButton("Aggiungi paziente");
        JButton listPaziente = new JButton("Lista pazienti");
       
        JTextField  nome = new JTextField(30);
       // nome.setBounds(20, 20, 20, 20);
        JTextField  cognome = new JTextField(30);
        //nome.setBounds(30, 20, 20, 20);
        JTextField data = new JTextField(10);
        JTextField sesso = new JTextField(1);
       // sesso.setBounds(30, 20, 20, 20);
        
        
        
        JLabel label1 = new JLabel("Nome : ");
        //label1.setLocation(100,100);
        JLabel label11 = new JLabel("Cognome : ");
        //label1.setLocation(100,100);
        JLabel label12 = new JLabel("Data di nascita : ");
        JLabel label13 = new JLabel("Sesso : ");
        //label1.setLocation(100,100);
        
        
        
        
        
        
        
        JLabel label2 = new JLabel("Medici");
        JLabel label3 = new JLabel("Macchinari");

        //Impostsazioni della tab 1 relativa ai pazienti
        
        panel1.add(label1,BorderLayout.WEST);
        panel1.add(nome,BorderLayout.CENTER);
        panel1.add(label11,BorderLayout.WEST);
        panel1.add(cognome,BorderLayout.CENTER);
        panel1.add(label12,BorderLayout.WEST);
        panel1.add(data, BorderLayout.CENTER);
        panel1.add(label13,BorderLayout.WEST);
        panel1.add(sesso, BorderLayout.CENTER);
        
        panel1.add(addPaziente);
        panel1.add(listPaziente);
        
        
        // Fine tab pazienti
        
        
        
        panel2.add(label2);
        panel3.add(label3);

        tabPane.add("Pazienti", panel1);
        tabPane.add("Medici", panel2);
        tabPane.add("Macchinari", panel3);
        tabPane.add("Gestione prenotazioni",panel4);
        //frame.add(tabPane);
        //panel.setLayout(new FlowLayout());
        //panel.add(jb1);
       frame.add(tabPane);
         
        // Set the window to be visible as the default to be false
        //frame.add(panel);
        //frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(400, 400));
        frame.setVisible(true);
        //frame.pack();
        
        
        
        
        addPaziente.addActionListener(new ActionListener(){
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		// We require at least Name and Surname
        		
        		if(!nome.getText().isEmpty() && !cognome.getText().isEmpty() && 
        				!data.getText().isEmpty()) { //check if nome , cognome and data are empty
        		
        		command.addPaziente(p.setNome(nome.getText()).setCognome(cognome.getText()).setNascita(data.getText()).setSesso(sesso.getText()));
        		
        		JOptionPane.showMessageDialog(
        		        null, "Paziente aggiunto", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        		}
        		else
        			JOptionPane.showMessageDialog(
            		        null, "Errore!\n Nome, cognome e data di nascita sono necessari", "ERRORE", JOptionPane.ERROR_MESSAGE);
        		
        	}
        	
        });
        
        listPaziente.addActionListener(new ActionListener(){
        	
        	public void actionPerformed(ActionEvent e) {
        		command.listPazienti();
        	}
        	
        });
		
	
		 
        
       
    }
	

}
