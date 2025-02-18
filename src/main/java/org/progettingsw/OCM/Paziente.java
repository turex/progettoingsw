package org.progettingsw.OCM;


public class Paziente{
	
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

	public String getNome() {
		// TODO Auto-generated method stub
		return nome;
	}

	public String getCognome() {
		// TODO Auto-generated method stub
		return cognome;
	}

	public String getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	public String getNascita() {
		// TODO Auto-generated method stub
		return nascita;
	}
	
	public String getSesso() {
		// TODO Auto-generated method stub
		return sesso;
	}
	

	public String toString() {
		return "Paziente [nome=" + nome + ", cognome=" + cognome + ", ID=" + ID + ", nascita="
				+ nascita + ", sesso=" + sesso + "]";
	}

	
	
}
