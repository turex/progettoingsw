package org.progettingsw.OCM.panels;

import java.awt.GridLayout;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PaymentPanel {
	
	  static CommonPanelUtils common = CommonPanelUtils.getInstance();

	  private JTextField daPagare;
	  
	  private int importo = 0;
	  
	  PaymentPanel(int importo){
		  
		  
	  }
	
	public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10)); // GridLayout con 2 colonne e 10 pixel di spazio tra le righe e le colonne
        
        daPagare = common.createTextField("Costo totale:"); // Lunghezza preferita per il campo Nome
        //cognome = common.createTextField("Cognome:"); // Lunghezza preferita per il campo Cognome
        //dateSpinner = common.createSpinner("dd/MM/yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //genderSpinner = common.createSpinner(new String[]{"M", "F"});
        
        panel.add(new JLabel("Nome:"));
       // panel.add(nome);
        panel.add(new JLabel("Cognome:"));
       // panel.add(cognome);
        panel.add(new JLabel("Data di nascita:"));
       // panel.add(dateSpinner);
        panel.add(new JLabel("Sesso:"));
        //panel.add(genderSpinner);
		
        
        //panel.add(addPaziente);
        //panel.add(listPaziente);
       // panel.add(saveDBP);
return panel;
}
}
