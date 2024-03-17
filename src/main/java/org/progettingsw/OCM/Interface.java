package org.progettingsw.OCM;

import org.progettingsw.OCM.panels.MacchinarioPanel;
import org.progettingsw.OCM.panels.MedicoPanel;
import org.progettingsw.OCM.panels.PazientePanel;
import org.progettingsw.OCM.panels.PrenotazioniPanel;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

/*
 * 
 * Classe contenente il main e la parte grafica
 * OCM Opensource clinical manager
 * Gestionale di una clinica, viene simulata la gestione dei pazienti, 
 * dei medici e delle prenotazioni di una clinica. 
 * 
 * Usiamo il Builder pattern per medico, paziente e prenotazione
 * 
 */

public class Interface {

	static String selectPaziente; // mi da l'item del paziente
	static String selectionProfessione;// mi da l'item della professione
	final String PATH = System.getProperty("user.dir"); // Path corrente dell'eseguibile
	File pf, mf; // file per check db()p paziente , m medico

	static String Id; // inserisco in questa variabile ID di medico o paziente durante la "Creazione"

	static JsonHelper dbs = JsonHelper.getIstance(); // creo oggetto JSON
	static ComandiPaziente command = new ComandiPaziente();
	static ComandiMedico medcommand = new ComandiMedico();
	static ComandiPrenotazione prencommand = new ComandiPrenotazione();

	static PazienteBuilder p = new PazienteBuilder();
	static MedicoBuilder m = new MedicoBuilder();
	static PrenotazioneBuilder pb = new PrenotazioneBuilder();

	ArrayList<String> lista_medico = new ArrayList<String>();

	final String TITOLO = "OCM Opensource clinic manager";

	int X = 500;
	int Y = 500;
	
	public Interface() {

		JFrame frame = new JFrame(TITOLO);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(X, Y);

		JTabbedPane tabbedPane = new JTabbedPane();

		tabbedPane.addTab("Pazienti", new PazientePanel().createPanel());
		tabbedPane.addTab("Medici", new MedicoPanel().createPanel());
		tabbedPane.addTab("Macchinari", new MacchinarioPanel().createPanel());
		tabbedPane.addTab("Prenotazioni", new PrenotazioniPanel().createPanel());

		frame.add(tabbedPane);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.pack();

		int list_size;

		/*
		 * 
		 * All'avvio dell'interfaccia cerco di caricare i database
		 * 
		 */

		pf = new File(PATH + "\\Paziente.json");
		mf = new File(PATH + "\\Medico.json");
		if (pf.exists()) {

			// for(int i = 0; i < list_size; i++) {
			// command.addPaziente(p.setNome(dbs.np.get(i)).setCognome(dbs.cp.get(i)).setID(dbs.ip.get(i)).setNascita(dbs.nap.get(i))
			// .setSesso(dbs.sp.get(i)));

			// dbs.addtoJson(dbs.np.get(i), dbs.cp.get(i),dbs.ip.get(i) ,null ,
			// dbs.nap.get(i), dbs.sp.get(i), "Paziente");

			// }

		}
		if (mf.exists()) {
			dbs.readDb("Medico");
			list_size = 0;
			list_size = dbs.getlistSize("Medico");
			System.out.println(list_size);

		} else
			System.out.println("Errore sui database");

		/*
		 * 
		 * Carico il database
		 * 
		 */

	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> new Interface());
		
        
      /*  
                
      //Impostazioni della tab 1 relativa ai pazienti
       
        //JButton addPaziente = new JButton("Aggiungi paziente");
        //JButton listPaziente = new JButton("Lista pazienti");
        //JButton saveDBP = new JButton("Salva Database");
       
        //JTextField nome = new JTextField(30);
        //JTextField cognome = new JTextField(30);
        //JTextField sesso = new JTextField(1);
        
        /*
         * 
         * Il SimpledataFormat mi crea una formattazione utile per le date
         * Poi creo un TextField formattato con formattazione creata in precedenza
         
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formattazione data
        JFormattedTextField data = new JFormattedTextField(sdf);
        data.setPreferredSize(new Dimension(100, 20));
        data.setValue(new Date());
        
        JLabel label1 = new JLabel("Nome : ");
        JLabel label11 = new JLabel("Cognome : ");
        JLabel label12 = new JLabel("Data di nascita : ");
        JLabel label13 = new JLabel("Sesso : ");
        
        //panel1.add(label1,BorderLayout.WEST);
        //panel1.add(nome,BorderLayout.CENTER);
        //panel1.add(label11,BorderLayout.WEST);
        //panel1.add(cognome,BorderLayout.CENTER);
        //panel1.add(label12,BorderLayout.WEST);
        //panel1.add(data, BorderLayout.CENTER);
        //panel1.add(label13,BorderLayout.WEST);
        //panel1.add(sesso, BorderLayout.CENTER);
        
        //panel1.add(addPaziente);
        //panel1.add(listPaziente);
        //panel1.add(saveDBP, BorderLayout.LINE_END);
        
        
        // Fine tab pazienti
        
      //Impostazioni della tab 2 relativa ai medici
        
        JButton addMedico = new JButton("Aggiungi medico");
        JButton listMedici = new JButton("Lista medici");
        JButton saveDBM = new JButton("Salva Database");
       
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
        panel2.add(saveDBM, BorderLayout.PAGE_END);
        
        
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
        
        
        JList<String> lp = new JList<String>(); //Lista pazienti
        JList<String> lprof = new JList<String>(); //Lista professioni medici
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm"); // Formattazione data
        JFormattedTextField data_prenotazione = new JFormattedTextField(sdf1);
        data_prenotazione.setPreferredSize(new Dimension(100, 20));
        data_prenotazione.setValue(new Date());
        
        
        JLabel l4 = new JLabel("Lista Pazienti : ");
        JLabel l41 = new JLabel("Lista professionisti : ");
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
        
        frame.setVisible(true);
        frame.setResizable(false);
        
        
        
        
        
        
        
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
        
        
        saveDBM.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dbs.writeJson("Medico");
				
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
                  selectPaziente = lp.getSelectedValue().toString();
                	}
                	catch (NullPointerException e)
                	{
                		e.printStackTrace();
                	}
                  System.out.println(selectPaziente);
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
        		String[] split_paziente= {};
        		String[] split_medico= {};
        		
        		if(!(selectionProfessione == null) && !(selectPaziente == null)) { // aggiungo la prenotazione se ho selezionato sia paziente che medico ma
        			//non é presente gia una prenotazion
        			split_paziente = selectPaziente.split(" ");
        			split_medico = selectionProfessione.split(" ");
        			        		
        			if(!prencommand.checkPrenotazione(split_paziente[2],split_medico[3],split_medico[1], data_prenotazione.getText())&&
        					!prencommand.checkdispoMedico(split_medico[0],split_medico[1], split_medico[2], data_prenotazione.getText())) { //se il check é false allora mi aggiunge la prenotazione
        				//System.out.println(prencommand.checkPrenotazione(split_paziente[0],split_paziente[1], split_medico[0],split_medico[1], split_medico[2], data_prenotazione.getText())); //DEBUG

        				
        				prencommand.addPrenotazione(pb.setidPaziente(split_paziente[2]).setidMedico(split_medico[3]).setProfessione(split_medico[2]).setData(data_prenotazione.getText()));
        				dbs.addPrenotazioni(split_paziente[2], split_medico[3], split_medico[2]);
        				
        				
        				new Popup("SUCCESSO","Prenotazione aggiunta!");
        			}
        			else
        				new Popup("ERRORE","Prenotazione gia presente , medico non disponibile o campi vuoti");
        		}
        		else
        			new Popup("ERRORE", "Seleziona medico e paziente!");
        		
        		
        	}
        	
        });
        
        listPrenotazioni.addActionListener(new ActionListener(){
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		try
        		{
        			
        		String[] split_paziente = selectPaziente.split(" ");
        			
        		if(!(selectPaziente == null) && !(split_paziente == null)) {
        		prencommand.listPrenotazioni(split_paziente[2]);
        		}
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

	*/


	}
}
// End class
