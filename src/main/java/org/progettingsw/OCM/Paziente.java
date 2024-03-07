package org.progettingsw.OCM;


public class Paziente implements Uomo{
	
	String nome = "";
	String cognome = "";
	String ID = "";
	String nascita = "";
	String sesso = "";
	
public Paziente(String nome,String cognome,String ID,String nascita,String sesso) {
		this.nome = nome;
		this.cognome = cognome;
		this.ID = ID;
		this.nascita = nascita;
		this.sesso = sesso;
	}

	@Override
	public String getNome() {
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

	@Override
	public String toString() {
		return "Paziente [nome=" + nome + ", cognome=" + cognome + ", ID=" + ID + ", nascita="
				+ nascita + ", sesso=" + sesso + "]";
	}

	
	
}
