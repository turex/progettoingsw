package org.progettingsw.OCM;

import org.progettingsw.OCM.panels.MacchinarioPanel;
import org.progettingsw.OCM.panels.MedicoPanel;
import org.progettingsw.OCM.panels.PazientePanel;
import org.progettingsw.OCM.panels.PrenotazioniPanel;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

/*
 * 
 * Classe contenente il main con la parte grafica
 * OCM Opensource clinical manager
 * Gestionale di una clinica, viene simulata la gestione dei pazienti, 
 * dei medici e delle prenotazioni di una clinica. 
 * 
 * Usiamo il Builder pattern per medico, paziente e prenotazione
 * 
 */

public class Interface {

	final String PATH = System.getProperty("user.dir"); // Path corrente dell'eseguibile
	File pf, mf, ppf; // file per check db()p paziente , m medico, pp prenotazione

	static JsonHelper dbs = JsonHelper.getIstance(); // creo oggetto JSON
	
	JFrame frame;

	private final String TITOLO = "OCM Opensource clinic manager";

	private final int X = 500;
	private final int Y = 500;
	
	public Interface() {

		frame = new JFrame(TITOLO);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(X, Y);

		JTabbedPane tabbedPane = new JTabbedPane();
		
		PrenotazioniPanel pren = PrenotazioniPanel.getIstance();

		tabbedPane.addTab("Pazienti", new PazientePanel().createPanel());
		tabbedPane.addTab("Medici", new MedicoPanel().createPanel());
		tabbedPane.addTab("Macchinari", new MacchinarioPanel().createPanel());
		tabbedPane.addTab("Prenotazioni", pren.createPanel());

		frame.add(tabbedPane);
		
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		
		/*
		 * 
		 * All'avvio dell'interfaccia cerco di caricare interfaccia grafica e database
		 * 
		 */

		
		loadDB();

		

	}

	public static void main(String[] args) {
		
		//SwingUtilities.invokeLater(() -> new Interface());
		new Interface();
	}
	
	private void loadDB() {
		
		pf = new File(PATH + "\\Paziente.json");
		mf = new File(PATH + "\\Medico.json");
		ppf = new File(PATH + "\\Prenotazione.json");
		if (pf.exists()) {

			dbs.readfromJson("Paziente");
		}
		if (mf.exists()) {
			dbs.readfromJson("Medico");

		} 
		
		if (ppf.exists()) {
			dbs.readfromJson("Prenotazione");

		} 
	}
	
	
	
	
}
