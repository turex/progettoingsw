package main;

public class PazienteBuilder {
	
	String nome = "";
	String cognome = "";
	int ID = 0;
	String nascita = "";
	String sesso = "";
	String prenotazione = "";

	public PazienteBuilder setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public PazienteBuilder setCognome(String cognome) {
		this.cognome = cognome;
		return this;

	}

	public PazienteBuilder setID(int ID) {
		this.ID = ID;
		return this;

	}

	public PazienteBuilder setNascita(String nascita) {
		this.nascita = nascita;
		return this;

	}
	
	public PazienteBuilder setSesso(String sesso) {
		this.sesso = sesso;
		return this;
	}
	
	public PazienteBuilder setPrenotazione(String prenotazione) {
		this.prenotazione = prenotazione;
		return this;
	}
	
	public Paziente getPaziente() {
		return new Paziente(nome,cognome,ID,nascita,sesso,prenotazione);
	}
	
	

}
