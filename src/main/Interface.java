package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        
        
        JTextField  nome = new JTextField(20);
        
        JLabel label1 = new JLabel("Pazienti");
        JLabel label2 = new JLabel("Medici");
        JLabel label3 = new JLabel("Macchinari");

        panel1.add(label1);
        panel1.add(nome).setLocation(20,20);
        panel2.add(label2);
        panel3.add(label3);

        tabPane.add("Pazienti", panel1);
        tabPane.add("Medici", panel2);
        tabPane.add("Macchinari", panel3);
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
        command.addPaziente(p.setNome("a").setCognome("i").setID(0).setEta(0).setNascita("20/12/231").setSesso("M").setPrenotazione("22/12/2023"));
        command.listPazienti();
        
    }

}
