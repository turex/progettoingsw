package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;


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
	static String selectionItem; //mi da l'item del paziente
	static String selectionProfessione;// mi da l'item della professione
	
	public static void main(String[] args) {
		
		final String TITOLO = "OCM Opensource clinic manager";

        JFrame frame = new JFrame(TITOLO);
                
        ComandiPaziente command = new ComandiPaziente();
        ComandiMedico medcommand = new ComandiMedico();
        ComandiPrenotazione prencommand = new ComandiPrenotazione();
        
        PazienteBuilder p = new PazienteBuilder();
        MedicoBuilder m = new MedicoBuilder();
        PrenotazioneBuilder pb = new PrenotazioneBuilder();
        
         
        // Define the panel 
        JTabbedPane tabPane = new JTabbedPane();//TAB Panel
        
        JPanel panel1 = new JPanel();// Pannello "Paziente" dove vengono inseriti i dati del paziente
        JPanel panel2 = new JPanel();// Pannello "Medico" dove vengono inseriti i dati del Medico
        JPanel panel3 = new JPanel();// Pannello "Macchinario" dove vengono inseriti i dati dei macchinari
        JPanel panel4 = new JPanel(new BorderLayout());// Pannello "Prenotazioni" dove vengono inseriti i dati delle prenotazioni
        
        
        
      //Impostazioni della tab 1 relativa ai pazienti
       
        JButton addPaziente = new JButton("Aggiungi paziente");
        JButton listPaziente = new JButton("Lista pazienti");
       
        JTextField nome = new JTextField(30);
        JTextField cognome = new JTextField(30);
        JTextField sesso = new JTextField(1);
        
        /*
         * 
         * Il SimpledataFormat mi crea una formattazione utile per le date
         * Poi creo un TextField formattato con formattazione creata in precedenza
         */
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formattazione data
        JFormattedTextField data = new JFormattedTextField(sdf);
        data.setPreferredSize(new Dimension(100, 20));
        data.setValue(new Date());
        
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
        
        JLabel label2 = new JLabel("Nome : ");
        JLabel label21 = new JLabel("Cognome : ");
        JLabel label22 = new JLabel("Professione : ");
        
        panel2.add(label2,BorderLayout.WEST);
        panel2.add(nome2,BorderLayout.CENTER);
        panel2.add(label21,BorderLayout.WEST);
        panel2.add(cognome2,BorderLayout.CENTER);
        panel2.add(label22,BorderLayout.WEST);
        panel2.add(professione, BorderLayout.CENTER);
       
        panel2.add(addMedico);
        panel2.add(listMedici);
        
        
        // Fine tab Medici
        
        
        //TODO :: Da fare TAB 3
        
        //
        //
        //
        
        
        //Impostazioni della tab 4 relativa alle prenotazioni
        
        
        JButton addPrenotazione = new JButton("Aggiungi prenotazione");
        JButton listPrenotazioni = new JButton("Lista prenotazioni");
        
        JPanel sinistro = new JPanel(new BorderLayout());//Inserisco la lista pazienti
        JPanel giu = new JPanel(new BorderLayout()); // Inserisco i bottoni
        JPanel destro = new JPanel(new BorderLayout()); // Inserisco i bottoni
        JPanel centro = new JPanel(new BorderLayout());//Inserisco la lista pazienti
        
        
        JList lp = new JList(); //Lista pazienti
        JList lprof = new JList(); //Lista professioni medici
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm"); // Formattazione data
        JFormattedTextField data_prenotazione = new JFormattedTextField(sdf1);
        data_prenotazione.setPreferredSize(new Dimension(100, 20));
        data_prenotazione.setValue(new Date());
        
        
        JLabel l4 = new JLabel("Lista Pazienti : ");
        JLabel l41 = new JLabel("Lista professioni : ");
        JLabel l42 = new JLabel("Inserisci data prenotazione : ");
        
        
        sinistro.add(l4,BorderLayout.NORTH);
        sinistro.add(lp,BorderLayout.EAST);
        
        giu.add(addPrenotazione,BorderLayout.WEST);
        giu.add(listPrenotazioni,BorderLayout.EAST);
        
        destro.add(l41,BorderLayout.NORTH);
        destro.add(lprof,BorderLayout.EAST);
        
        centro.add(l42,BorderLayout.NORTH);
        centro.add(data_prenotazione,BorderLayout.SOUTH);
        
        panel4.add(centro,BorderLayout.CENTER);
        panel4.add(destro, BorderLayout.EAST);
        panel4.add(sinistro, BorderLayout.WEST);
        panel4.add(giu,BorderLayout.SOUTH);
        
        // Fine tab Medici
        


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
        		
        		if(!command.checkPaziente(nome.getText(),cognome.getText(),data.getText())) { //check if nome , cognome and data are empty and if is not still on database
        		command.addPaziente(p.setNome(nome.getText()).setCognome(cognome.getText()).setNascita(data.getText()).setSesso(sesso.getText()));

        		lp.setModel(command.listaPazientitoString()); //ottengo la lista pazienti e la inserisco nella listbox
        		
        		JOptionPane.showMessageDialog(
        		        null, "Paziente aggiunto!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        		
        		
        		//Lo aggiungo alla lista
        		
        		}
        		//}
        		else
        			JOptionPane.showMessageDialog(
            		        null, "Errore!\nNome, cognome e data di nascita sono necessari o paziente gia registrato", "ERRORE", JOptionPane.ERROR_MESSAGE);
        		
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
        		
        		if(!medcommand.checkMedico(nome2.getText(),cognome2.getText(),professione.getText())) { //check if nome , cognome and data are empty
        		
        		medcommand.addMedico(m.setNome(nome2.getText()).setCognome(cognome2.getText()).setProfessione(professione.getText()));
        		
        		lprof.setModel(medcommand.listaMedicitoString());
        		
        		
        		JOptionPane.showMessageDialog(
        		        null, "Medico aggiunto!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
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
        
        //ADD Macchinario
        
        //
        
        ///
        ///
        ///
        ////
        
        ////
        
        
        
        
        
        lp.addListSelectionListener(new ListSelectionListener() { //Setto il Listener per selezione dati da JList PAZIENTEL

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                	try {
                		
                		if(!(lp.getSelectedValue() == null))
                  selectionItem = lp.getSelectedValue().toString();
                	}
                	catch (NullPointerException e)
                	{
                		e.printStackTrace();
                	}
                  System.out.println(selectionItem);
                }
            }
        });
        
        
        lprof.addListSelectionListener(new ListSelectionListener() { //Setto il Listener per selezione dati da JList MEDICO

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                	try {
                		
                		if(!(lprof.getSelectedValue() == null))
                  selectionProfessione = lprof.getSelectedValue().toString();
                	}
                	catch (NullPointerException e)
                	{
                		e.printStackTrace();
                	}
                  System.out.println(selectionProfessione);
                }
            }
        });
        
        
        
        addPrenotazione.addActionListener(new ActionListener(){
        	
        	public void actionPerformed(ActionEvent e) {
        		String[] split_medico= {};
        		
        		if(!(selectionProfessione == null) && !(selectionItem == null)) { // aggiungo la prenotazione se ho selezionato sia paziente che medico ma
        			//non é presente gia una prenotazion
        		split_medico = selectionProfessione.split(" ");
        		
        			if(!prencommand.checkPrenotazione(selectionItem, split_medico[0], split_medico[2], data_prenotazione.getText(), TITOLO, TITOLO)) {
        				prencommand.addPrenotazione(pb.setnomePaziente(selectionItem).setnomeMedico(split_medico[0]).setProfessione(split_medico[2]).setData(data_prenotazione.getText()));
        		
        				new Popup("SUCCESSO","Prenotazione aggiunta!");
        			}
        			else
        				new Popup("ERRORE","Prenotazione gia presente o campi vuoti");
        		}
        		else
        			new Popup("ERRORE", "Seleziona medico e paziente!");
        		
        		
        	}
        	
        });
        
        listPrenotazioni.addActionListener(new ActionListener(){
        	
        	public void actionPerformed(ActionEvent e) {
        		try
        		{
        		if(!(selectionItem == null))
        		prencommand.listPrenotazioni(selectionItem);
        		else
        			new Popup("ERROE", "Seleziona Paziente");
        		}
        		catch (NullPointerException e1)
        		{
        			e1.printStackTrace();
        		}
        	}
        	
        });
        
		
		 
    }// end Main
	

}//End class
