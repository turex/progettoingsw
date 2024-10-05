package org.progettingsw.OCM;

public class pagaPayPal implements Pagamenti {

	boolean statoPagamento = false;
	
	
	@Override
	public String metodoPagamento() {
		// TODO Auto-generated method stub
		return "PayPal";
	}

	@Override
	public int conto() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean stato() {
		// TODO Auto-generated method stub
		return statoPagamento;
	}


}
