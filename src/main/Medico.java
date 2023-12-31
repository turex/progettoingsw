package main;

public class Medico implements Uomo{
	
	String nome = "";
	String cognome = "";
	int ID = 0;
	int eta = 0;
	String nascita = "";
	String sesso = "";
	
	public Medico(String nome,String cognome,int ID,int eta,String nascita,String sesso) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.ID = ID;
		this.eta = eta;
		this.nascita = nascita;
		this.sesso = sesso;
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return nome;
	}

	@Override
	public String getCognome() {
		// TODO Auto-generated method stub
		return cognome;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public int getEta() {
		// TODO Auto-generated method stub
		return eta;
	}

	@Override
	public String getNascita() {
		// TODO Auto-generated method stub
		return nascita;
	}

}
