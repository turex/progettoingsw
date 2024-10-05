package org.progettingsw.OCM;

import org.progettingsw.OCM.Popup.msgtype;

/*
 * 
 * Payment logic
 * 
 */

public class Pagamento{
	
	Pagamenti strategia;
	
	public Pagamento(){
		
	}

	public void pagato(Pagamenti strategia) {
		
		new Popup("Il cliente ha pagato tramite: " + strategia.metodoPagamento(), msgtype.OK);
		
		strategia.conto();
	}
	
}
	
  
