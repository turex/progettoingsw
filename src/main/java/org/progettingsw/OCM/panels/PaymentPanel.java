package org.progettingsw.OCM.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import org.progettingsw.OCM.Basket;
import org.progettingsw.OCM.Pagamenti;
import org.progettingsw.OCM.Pagamento;
import org.progettingsw.OCM.Popup;
import org.progettingsw.OCM.Popup.msgtype;
import org.progettingsw.OCM.pagaCartadiCredito;
import org.progettingsw.OCM.pagaPayPal;

public class PaymentPanel {
	
	  static CommonPanelUtils common = CommonPanelUtils.getInstance();

	  private JLabel daPagare;
	  
	  private JButton paga;
	  
	  private JSpinner metodoPagamento = common.createSpinner(new String[] {"PayPal", "Carta di Credito"});
	  
	  private int importo = 0;
	  String idpaziente = "";
	  
	  Pagamenti strategia; // Strategia di pagamento
	  Pagamento pagamento = new Pagamento();  //Pagamento finale
	  
	  PaymentPanel(int importo, String idpaziente){
		  
		  this.importo = importo;
		  this.idpaziente = idpaziente;
		  
	  }
	
	  
	public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10)); // GridLayout con 2 colonne e 10 pixel di spazio tra le righe e le colonne
        
        daPagare = new JLabel("Costo totale:" + importo); // Lunghezza preferita per il campo Nome
        paga = new JButton("Paga");
        
        panel.add(daPagare);
      
        panel.add(paga);
        
        panel.add(metodoPagamento);
        
        
        paga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String selezione = metodoPagamento.getValue().toString(); // Immagazino in questa variabile la mia scelta di pagamento
            	
            	if(importo > 0) {
            	switch(selezione) {
            		
            		case "PayPal":
            			strategia = new pagaPayPal();
            			break;
            		case "Carta di Credito":
            			strategia = new pagaCartadiCredito();
            		default:
            			break;
            		
            	}
            	
            	pagamento.pagato(strategia,idpaziente);
            	
            	}
            	
            	else {
            		new Popup("Non ci sono visite da pagare", msgtype.ERR);
            	}
            }
            	
        });
        
        
        return panel;
 }
	

}
