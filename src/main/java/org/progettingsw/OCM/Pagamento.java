package org.progettingsw.OCM;

import org.progettingsw.OCM.Popup.msgtype;

/*
 * 
 * Payment logic
 * 
 */

public class Pagamento{
	
	Pagamenti strategia;
	Basket basket = Basket.getIstance();
	
	public Pagamento(){
		
	}

	public void pagato(Pagamenti strategia, String idpaziente) {
		
		new Popup("Il cliente con ID : " + idpaziente +  " ha pagato tramite: " + strategia.metodoPagamento(), msgtype.OK);
		
		basket.clearBasket(idpaziente);
	}
	
}
	
  
