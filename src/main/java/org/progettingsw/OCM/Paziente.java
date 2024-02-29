package org.progettingsw.OCM;


public class Paziente implements Uomo{
	
	String nome = "";
	String cognome = "";
	String ID = "";
	String nascita = "";
	String sesso = "";
	String prenotazione = "";
	
public Paziente(String nome,String cognome,String ID,String nascita,String sesso, String prenotazione) {
		this.nome = nome;
		this.cognome = cognome;
		this.ID = ID;
		this.nascita = nascita;
		this.sesso = sesso;
		this.prenotazione = prenotazione;
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

	public String getPrenotazione() {
		// TODO Auto-generated method stub
		return prenotazione;
	}

	@Override
	public String toString() {
		return "Paziente [nome=" + nome + ", cognome=" + cognome + ", ID=" + ID + ", nascita="
				+ nascita + ", sesso=" + sesso + ", prenotazione=" + prenotazione + "]";
	}

	
	
}
