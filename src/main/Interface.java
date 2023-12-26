package main;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


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

        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(400, 400));
        frame.setVisible(true);
        
        
        
		/*
		 * PazienteBuilder p = new PazienteBuilder();
		 * 
		 * List<Paziente> users = new ArrayList<>(10); for (int i = 0; i < 10; i++) {
		 * users.add(p.setName("toto" + i).setCognome("Ferrari").getPaziente()); }
		 * 
		 * System.out.println(users.get(2));
		 */
        
        command.addPaziente(p.setNome("q").setCognome("i").setID(0).setEta(0).setNascita("21/12/2311").setSesso("M"));
        //command.printPaziente(0);
        
        command.addPaziente(p.setNome("q").setCognome("i").setID(0).setEta(0).setNascita("21/12/231").setSesso("M"));
        command.addPaziente(p.setNome("q").setCognome("i").setID(0).setEta(0).setNascita("20/12/231").setSesso("M"));
        command.listPazienti();
        
    }

}
