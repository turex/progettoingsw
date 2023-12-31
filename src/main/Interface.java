package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
        ComandiMedico medcommand = new ComandiMedico();
        PazienteBuilder p = new PazienteBuilder();
        MedicoBuilder m = new MedicoBuilder();
         
         
        // Define the panel to hold the buttons 
        JTabbedPane tabPane = new JTabbedPane();//TAB Panel
        
        JPanel panel1 = new JPanel();// Pannello "Paziente" dove vengono inseriti i dati del paziente
        JPanel panel2 = new JPanel();// Pannello "Medico" dove vengono inseriti i dati del Medico
        JPanel panel3 = new JPanel();// Pannello "Macchinario" dove vengono inseriti i dati dei macchinari
        JPanel panel4 = new JPanel();// Pannello "Prenotazioni" dove vengono inseriti i dati delle prenotazioni
        
        
        
      //Impostazioni della tab 1 relativa ai pazienti
       
        JButton addPaziente = new JButton("Aggiungi paziente");
        JButton listPaziente = new JButton("Lista pazienti");
       
        JTextField nome = new JTextField(30);
        JTextField cognome = new JTextField(30);
        JTextField data = new JTextField(10);
        JTextField sesso = new JTextField(1);
        
        JLabel label1 = new JLabel("Nome : ");
        JLabel label11 = new JLabel("Cognome : ");
        JLabel label12 = new JLabel("Data di nascita : ");
        JLabel label13 = new JLabel("Sesso : ");
        
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
        
      //Impostazioni della tab 2 relativa ai medici
        
        JButton addMedico = new JButton("Aggiungi medico");
        JButton listMedici = new JButton("Lista medici");
       
        JTextField nome2 = new JTextField(30);
        JTextField cognome2 = new JTextField(30);
        JTextField professione = new JTextField(10);
        //JTextField sesso = new JTextField(1);
        
        JLabel label2 = new JLabel("Nome : ");
        JLabel label21 = new JLabel("Cognome : ");
        JLabel label22 = new JLabel("Professione : ");
        //JLabel label23 = new JLabel("Sesso : ");
        
        panel2.add(label2,BorderLayout.WEST);
        panel2.add(nome2,BorderLayout.CENTER);
        panel2.add(label21,BorderLayout.WEST);
        panel2.add(cognome2,BorderLayout.CENTER);
        panel2.add(label22,BorderLayout.WEST);
        panel2.add(professione, BorderLayout.CENTER);
        //panel2.add(label23,BorderLayout.WEST);
        //panel2.add(sesso2, BorderLayout.CENTER);
        panel2.add(addMedico);
        panel2.add(listMedici);
        
        
        // Fine tab pazienti
        
        //panel2.add(label2);
        //panel3.add(label3);

        tabPane.add("Pazienti", panel1);
        tabPane.add("Medici", panel2);
        tabPane.add("Macchinari", panel3);
        tabPane.add("Gestione prenotazioni",panel4);
        
        frame.add(tabPane);

        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(400, 400));
        frame.setVisible(true);
        frame.setResizable(false);
        
        addPaziente.addActionListener(new ActionListener(){
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		// We require at least Name, Surname and date
        		
        		if(!nome.getText().isEmpty() && !cognome.getText().isEmpty() && 
        				!data.getText().isEmpty()) { //check if nome , cognome and data are empty
        		
        		command.addPaziente(p.setNome(nome.getText()).setCognome(cognome.getText()).setNascita(data.getText()).setSesso(sesso.getText()));
        		
        		JOptionPane.showMessageDialog(
        		        null, "Paziente aggiunto", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        		}
        		else
        			JOptionPane.showMessageDialog(
            		        null, "Errore!\nNome, cognome e data di nascita sono necessari", "ERRORE", JOptionPane.ERROR_MESSAGE);
        		
        	}
        	
        });
        
        listPaziente.addActionListener(new ActionListener(){
        	
        	public void actionPerformed(ActionEvent e) {
        		command.listPazienti();
        	}
        	
        });
        
        
        
        // ActionEvent related to Medico
        
        
addMedico.addActionListener(new ActionListener(){
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		// We require at least Name, Surname and date
        		
        		if(!nome2.getText().isEmpty() && !cognome2.getText().isEmpty() && 
        				!professione.getText().isEmpty()) { //check if nome , cognome and data are empty
        		
        		medcommand.addMedico(m.setNome(nome2.getText()).setCognome(cognome2.getText()).setProfessione(professione.getText()));
        		
        		JOptionPane.showMessageDialog(
        		        null, "Medico aggiunto", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        		}
        		else
        			JOptionPane.showMessageDialog(
            		        null, "Errore!\nTutti i campi sono necessari", "ERRORE", JOptionPane.ERROR_MESSAGE);
        		
        	}
        	
        });
        
        listMedici.addActionListener(new ActionListener(){
        	
        	public void actionPerformed(ActionEvent e) {
        		medcommand.listMedici();
        	}
        	
        });
		
	
		 
        
       
    }
	

}
