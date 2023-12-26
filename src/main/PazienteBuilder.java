package main;

public class PazienteBuilder {
	
	String nome = "";
	String cognome = "";
	int ID = 0;
	int eta = 0;
	String nascita = "";
	String sesso = "";

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

	public PazienteBuilder setEta(int eta) {
		this.eta = eta;
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
	
	public Paziente getPaziente() {
		return new Paziente(nome,cognome,ID,eta,nascita,sesso);
	}
	
	

}
