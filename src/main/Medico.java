package main;

public class Medico implements Uomo{
	
	String nome = "";
	String cognome = "";
	String ID = "";
	String nascita = "";
	String sesso = "";
	
	public Medico(String nome,String cognome,String ID,String nascita,String sesso) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.ID = ID;
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
	public String getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public String getNascita() {
		// TODO Auto-generated method stub
		return nascita;
	}

}
