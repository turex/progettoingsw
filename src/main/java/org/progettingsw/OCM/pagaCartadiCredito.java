package org.progettingsw.OCM;

public class pagaCartadiCredito implements Pagamenti{
	
	boolean statoPagamento = false;

	@Override
	public String metodoPagamento() {
		// TODO Auto-generated method stub
		statoPagamento = true;
		return "Carta di Credito";
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
