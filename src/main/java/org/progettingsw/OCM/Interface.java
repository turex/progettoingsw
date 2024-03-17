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
	File pf, mf; // file per check db()p paziente , m medico

	static String Id; // inserisco in questa variabile ID di medico o paziente durante la "Creazione"

	static JsonHelper dbs = JsonHelper.getIstance(); // creo oggetto JSON
	
	
	

	ArrayList<String> lista_medico = new ArrayList<String>();
	
	int list_size;

	private final String TITOLO = "OCM Opensource clinic manager";

	private final int X = 500;
	private final int Y = 500;
	
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
		
		loadDB();

		/*
		 * 
		 * All'avvio dell'interfaccia cerco di caricare interfaccia grafica e database
		 * 
		 */


	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> new Interface());
		
	}
	
	private void loadDB() {
		
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
	}
	
}
// End class
